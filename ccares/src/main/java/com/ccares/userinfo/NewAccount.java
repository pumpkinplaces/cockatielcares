package com.ccares.userinfo;

import java.io.IOException;

import com.ccares.database.LoginDb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewAccount
 */
@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewAccount() {
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
		// TODO Auto-generated method stub
				//doGet(request, response);
				String username = request.getParameter("username");
				String password = request.getParameter("password");
			
				LoginBean loginbean = new LoginBean();
				loginbean.setUsername(username);
				loginbean.setPassword(password);
				loginbean.setPrevusernamelogin(username);
				loginbean.setPrevpasswordlogin(password);
				loginbean.setCurrentusername(username);
				
				ServletContext application = getServletContext();
				LoginDb loginDb = new LoginDb();
				boolean goodPassword = loginDb.passIsGood(password);
				boolean goodUsername = loginDb.usernameQualifies(username, application);
				boolean qualifies = goodPassword && goodUsername;
				
				String pt1 = "The password must be at least 8 characters long, ";
				String pt2 = "must contain both capital and lowercase letters, ";
				String pt3 = "and must contain at least 1 number or special character.";
				String errormessage = pt1 + pt2 + pt3;
				if (!goodPassword) {application.setAttribute("passwordError", errormessage);}
				else {application.removeAttribute("passwordError");}
				CcaresUser user = new CcaresUser();
				user.setUsername(username.toLowerCase());
				user.setPassword(password);
				application.setAttribute("almostUser", user);
				
				if (qualifies){
					application.removeAttribute("usernameError");
					application.removeAttribute("passwordError");
					response.sendRedirect("makeEmail.jsp");
				}
				else {
					response.sendRedirect("makeAcct.jsp");
				}
	}

}

