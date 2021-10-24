package com.ccares.email;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccares.database.LoginDb;
import com.ccares.userinfo.CcaresUser;
import com.ccares.userinfo.LoginBean;


/**
 * Servlet implementation class emailVerifier
 */
@WebServlet("/emailVerifier")
public class emailVerifier extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public emailVerifier() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean isInteger(String s) {
    	try{
    		Integer.parseInt(s);
    	}
    	catch(Exception e ){
    		return false;
    	}
    return true;
    }
    
    public void wrongVerificationCode(CcaresUser passworduser, ServletContext application, String errorMessage, HttpServletResponse response) throws IOException {
    	if (passworduser == null) {
			application.setAttribute("errorMessage", errorMessage);
			response.sendRedirect("verifyEmail.jsp");
		}
		else {
			application.setAttribute("wrongCode", errorMessage);
			response.sendRedirect("emailPChange.jsp"); 
		}
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
		String replyCodeStr = request.getParameter("replyCode");
		boolean isInt = isInteger(replyCodeStr);
		String errorMessage = "Sorry! You typed the wrong verification code. Please try again.";
		ServletContext application = getServletContext();
		CcaresUser passworduser = (CcaresUser) application.getAttribute("passworduser");
		if (!isInt) {
			if (passworduser != null) {
				application.setAttribute("wrongVerCode", errorMessage);
				application.setAttribute("passwordProcess", true);
			}
			wrongVerificationCode(passworduser, application, errorMessage, response);
			return;
		}
		int replyCode = Integer.parseInt(request.getParameter("replyCode"));
		
		LoginDb ldb = new LoginDb();
		if (EmailUtility.verificationCode == replyCode) {
			if (passworduser == null) {
				CcaresUser almostUser = (CcaresUser) application.getAttribute("almostUser");
				String email = almostUser.getEmail();
				HttpSession session = request.getSession();
				LoginBean lb = new LoginBean();
				lb.setUsername(almostUser.getUsername());
				lb.setPassword(almostUser.getPassword());
				ldb.insertElems(lb);
				ldb.updateEmailAddr(almostUser.getUsername(), email);
				session.setAttribute("user", almostUser);
				application.removeAttribute("almostUser");
				application.removeAttribute("errorMessage");
				response.sendRedirect("loginSuccess.jsp");
			}
			else {
				application.removeAttribute("canTestEmail");
				application.setAttribute("passwordProcess", true);
				application.removeAttribute("pwordProblem");
				response.sendRedirect("newPass.jsp");
			}
		}
		else {
			if (passworduser != null) {
				application.setAttribute("wrongVerCode", errorMessage);
				application.setAttribute("passwordProcess", true);
			}
			wrongVerificationCode(passworduser, application, errorMessage, response);
		}
	}

}

