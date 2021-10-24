package com.ccares.login;

import java.io.IOException;

import com.ccares.userinfo.CcaresUser;
import com.ccares.database.LoginDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ProfilePage
 */
@WebServlet("/ProfilePage")
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfilePage() {
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
		System.out.println("it came here! it came to profile page!");
		LoginDb ldb = new LoginDb();
		HttpSession session = request.getSession(false);
		CcaresUser user = (CcaresUser) session.getAttribute("user");
		String username = user.getUsername();
		String email = ldb.getEmail(username);
		System.out.println("This is the email: "+ email);
		user.setEmail(email);
		session.setAttribute("user", user);
		response.sendRedirect("makepfp.jsp");
	}

}

