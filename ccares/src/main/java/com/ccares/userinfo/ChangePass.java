package com.ccares.userinfo;

import java.io.IOException;


import com.ccares.database.LoginDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ChangePass
 */
@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChangePass() {
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
		LoginDb ldb = new LoginDb();
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String confirmedPass = request.getParameter("confirmedPass");
		HttpSession session = request.getSession(false);
		CcaresUser user = (CcaresUser) session.getAttribute("user");
		String username = user.getUsername();
		String realPass = user.getPassword();
		boolean oldPassGood = oldPass.equals(realPass);
		boolean newPassQualifies = ldb.passIsGood(newPass);
		boolean newPassGood = newPass.equals(confirmedPass);
		boolean samePass = oldPass.equals(newPass);
		boolean bothPassGood = newPassGood && newPassQualifies && !samePass;
		boolean anError = !oldPassGood || !bothPassGood || samePass;
		session.setAttribute("oldPassword", oldPass);
		session.setAttribute("newPassword", newPass);
		session.setAttribute("confirmedPass", confirmedPass);
		session.setAttribute("canChangePass", true);
		String newPerror = "The new password does not match the confirmed password";
		String oldPerror = "Sorry, the password you entered does not match the current password we have for this account.";
		String pt1 = "The password must be at least 8 characters long, ";
		String pt2 = "must contain both capital and lowercase letters, ";
		String pt3 = "and must contain at least 1 number or special character.";
		
		if (samePass) {
			newPerror = "The new password and old password are the same.";
		}
		else if (!newPassQualifies) {
			newPerror = pt1 + pt2 + pt3;
		}
		if (!oldPassGood) {
			session.setAttribute("oldPerror",oldPerror);
		}
		else {
			session.removeAttribute("oldPerror");
		}
		if (!bothPassGood) {
			session.setAttribute("newPerror", newPerror);
		}
		else {
			session.removeAttribute("newPerror");
		}
		if (anError) {
			session.setAttribute("aPasswordError", true);
			response.sendRedirect("changePass.jsp");
		}
		else {
			ldb.updatePassword(username, newPass);
			session.removeAttribute("canChangePass");
			session.removeAttribute("aPasswordError");
			session.invalidate();
			request.setAttribute("newPassSuccess", "Your password has been reset! Please log back into your account.");
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
	        requestDispatcher.forward(request, response); 
		}
		         
	}

}
