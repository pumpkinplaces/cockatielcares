package com.ccares.login;

import java.io.IOException;

import com.ccares.userinfo.CcaresUser;
import com.ccares.database.LoginDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class NewPass
 */
@WebServlet("/NewPass")
public class NewPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewPass() {
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
		ServletContext application = getServletContext();
		CcaresUser passworduser = (CcaresUser) application.getAttribute("passworduser");
		String username = passworduser.getUsername();
		String newPassword = request.getParameter("newPassword");
		application.setAttribute("newPassword", newPassword);
		String confirmedPassword = request.getParameter("confirmedPassword");
		application.setAttribute("confirmedPassword", confirmedPassword);
		boolean passwordsMatch = newPassword.equals(confirmedPassword);
		if (passwordsMatch) {
			LoginDb ldb = new LoginDb();
			boolean passwordQualifies = ldb.passIsGood(newPassword);
			if (!passwordQualifies) {
				String pt1 = "The password must be at least 8 characters long, ";
				String pt2 = "must contain both capital and lowercase letters, ";
				String pt3 = "and must contain at least 1 number or special character.";
				application.setAttribute("pwordProblem", pt1 + pt2 + pt3);
				application.setAttribute("passwordProcess", true);
				response.sendRedirect("newPass.jsp");
				return;
			}
			ldb.updatePassword(username, newPassword);
			application.removeAttribute("passwordProcess");
			application.removeAttribute("passworduser");
			application.removeAttribute("pwordProblem");
			application.removeAttribute("newPasword");
			application.removeAttribute("confirmedPassword");
			request.setAttribute("newPassSuccess", "Your password has been changed! Please log back into your account.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
			
		}
		else {
			application.setAttribute("pwordProblem", "The new password and confirmed password do not match.");
			application.setAttribute("passwordProcess", true);
			response.sendRedirect("newPass.jsp");
			
		}
	}

}

