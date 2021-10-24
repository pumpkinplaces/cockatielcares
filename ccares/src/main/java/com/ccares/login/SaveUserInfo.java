package com.ccares.login;

import java.io.IOException;

import com.ccares.userinfo.CcaresUser;
import com.ccares.userinfo.ProfilePic;
import com.ccares.database.LoginDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig(maxFileSize = 16177215)
/**
 * Servlet implementation class SaveUserInfo
 */
@WebServlet("/SaveUserInfo")
public class SaveUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SaveUserInfo() {
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
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		CcaresUser user = (CcaresUser) session.getAttribute("user");
		String newEmail = request.getParameter("email");
		String newUsername = request.getParameter("username");;
		String username = user.getUsername();
		ldb.updateAll(username, newUsername, newEmail, request);
		ProfilePic pfp = new ProfilePic(); 
		Part filepart = request.getPart("newPfp");
		
		if (filepart != null && filepart.getSize() > 0) {
			System.out.println("is going to change the profile picture");
			pfp.changePfpInDb(request, response, filepart);
		}
		else {
			System.out.println("the file part was null");
		}
		response.sendRedirect("makepfp.jsp");
	}
}

