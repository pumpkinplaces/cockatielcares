package com.ccares.login;

import java.io.IOException;


import com.ccares.userinfo.CcaresUser;
import com.ccares.helpers.HashGenerator;
import com.ccares.helpers.HashGeneratorException;
import com.ccares.helpers.TokenCreator;
import com.ccares.userinfo.LoginBean;
import com.ccares.database.LoginDb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		CcaresUser loginUser = new CcaresUser();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		ServletContext application = getServletContext();
		application.setAttribute("loginUser", loginUser);
		
		LoginDb loginDb = new LoginDb();
		
		boolean userExists = loginDb.usernameExists(username);
		
		String errorMessage = "The username and password do not match.";
		if (!userExists) {
			errorMessage = "There is no account with this username.";
		}
		else {
			boolean correctUserPass = loginDb.validatePassword(username, password);
			if (correctUserPass && userExists){	
				application.removeAttribute("loginUser");
				application.removeAttribute("loginError");
				LoginBean.setCurrentusernameJ(username);
				HttpSession session = request.getSession();
				CcaresUser user = new CcaresUser();
				user.setUsername(username.toLowerCase());
				user.setPassword(password);
				String email = loginDb.getEmail(username);
				user.setEmail(email.toLowerCase());
				String base64Pfp = loginDb.displayPfp(username);
				user.setProfilePic(base64Pfp);
				session.setAttribute("user", user);
				
				boolean rememberMe = request.getParameter("rememberMe") != null;
				if (rememberMe) {
					Cookie[] cookies = request.getCookies();
					String selector = "";
					String validator = "";	
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("selector")) {
							selector = cookie.getValue();
							}
							else if (cookie.getName().equals("validator")) {
								validator = cookie.getValue();
							}
						}
					}
					try {
						TokenCreator.createNewToken(loginDb, user, response, selector,  HashGenerator.generateSHA256(validator));
					} catch (HashGeneratorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				response.sendRedirect("makepfp.jsp");
				return;
			}
		}
		application.setAttribute("loginError", errorMessage);
		response.sendRedirect("login.jsp");
	}

}
