package com.ccares.helpers;

import java.io.IOException;

import com.ccares.userinfo.CcaresUser;
import com.ccares.database.LoginDb;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter("/*")
public class AdminAuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);	
		Cookie[] cookies = httpRequest.getCookies();
		
		String selector = "";
		String validator = "";
		
		if (session == null && cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("selector")) {
				selector = cookie.getValue();
				}
				else if (cookie.getName().equals("validator")) {
					validator = cookie.getValue();
				}
			}
		}
		
		boolean tokenExists = !selector.equals("") && !validator.equals("");
		if (session == null && tokenExists) {
			LoginDb loginDb = new LoginDb();
			String hashedValidator;
			try {
				hashedValidator = HashGenerator.generateSHA256(validator);
				CcaresUser user = loginDb.findBySelector(selector, hashedValidator);
				if (user != null) {
					HttpSession newSession = httpRequest.getSession();
					newSession.setAttribute("user", user);
					TokenCreator.createNewToken(loginDb, user, httpResponse, selector, hashedValidator);
				}
			} catch (HashGeneratorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}
	
	public AdminAuthenticationFilter() {
		
	}
	
	public void destroy() {
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException{
		
	}
}

