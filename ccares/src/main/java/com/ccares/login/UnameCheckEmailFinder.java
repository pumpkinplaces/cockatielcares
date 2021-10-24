package com.ccares.login;

import java.io.IOException;

import com.ccares.database.LoginDb;
import com.ccares.userinfo.CcaresUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UnameCheckEmailFinder
 */
@WebServlet("/UnameCheckEmailFinder")
public class UnameCheckEmailFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UnameCheckEmailFinder() {
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
		String username = request.getParameter("username");
		ServletContext application = getServletContext();
		application.setAttribute("passwordProcess", true);
		application.setAttribute("name", username);
		CcaresUser passworduser = new CcaresUser();
		String errorMessage = "There is no account with this username. Please try a different name.";
		LoginDb ldb = new LoginDb();
		boolean userExists = ldb.usernameExists(username);
		if (userExists) {
			application.setAttribute("usernameExists", true);
			passworduser.setUsername(username);
			String email = ldb.getEmail(username);
			passworduser.setEmail(email);
			application.setAttribute("passworduser", passworduser);
			response.sendRedirect("forgotPassword.jsp");
		}
		else {
			application.setAttribute("wrongUsername", errorMessage);
			response.sendRedirect("usernameFP.jsp");
			return;
		}
	}

}

