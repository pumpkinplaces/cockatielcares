package com.ccares.login;

import java.io.IOException;

import com.ccares.helpers.HashGenerator;
import com.ccares.helpers.HashGeneratorException;
import com.ccares.database.LoginDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Logout() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String selector = "";
			String rawValidator = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("selector")) {
					selector = cookie.getValue();
				}
				else if (cookie.getName().equals("validator")){
					rawValidator = cookie.getValue();
				}
			}
			
			if (!selector.equals("") && !rawValidator.equals("")) {
				LoginDb ldb = new LoginDb();
				String hashedValidator;
				try {
					hashedValidator = HashGenerator.generateSHA256(rawValidator);
					ldb.removeToken(selector, hashedValidator);
					Cookie selectorCookie = new Cookie("selector", "");
					selectorCookie.setMaxAge(0);
					
					Cookie validatorCookie = new Cookie("validator", "");
					validatorCookie.setMaxAge(0);
					
					response.addCookie(selectorCookie);
					response.addCookie(validatorCookie);
				} catch (HashGeneratorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		response.sendRedirect("index.jsp");
	}

}
