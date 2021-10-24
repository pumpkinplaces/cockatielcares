package com.ccares.userinfo;

import java.io.IOException;
import java.io.InputStream;

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
 * Servlet implementation class ProfilePic
 */
@WebServlet("/ProfilePic")
public class ProfilePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static InputStream inputStream = null;

    /**
     * Default constructor. 
     */
    public ProfilePic() {
        // TODO Auto-generated constructor stub
    }

    public void changePfpInDb(HttpServletRequest request, HttpServletResponse response, Part filePart) 
    		throws IOException, ServletException {
    	LoginDb ldb = new LoginDb();
		HttpSession session = request.getSession(false);
		CcaresUser user = (CcaresUser) session.getAttribute("user");
		String username = user.getUsername();
		System.out.println("this is the username: " + username);
		inputStream = filePart.getInputStream();
		System.out.println("it got the part");
		String pfpString = ldb.getPfpString(inputStream);
		user.setProfilePic(pfpString);
		inputStream =filePart.getInputStream();
		session.setAttribute("user", user);
		ldb.updatePfp(username);

		
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
		Part filepart = request.getPart("apfpfile");
		if (filepart != null){
			System.out.println("the file was not null");
			changePfpInDb(request, response, filepart);
			response.sendRedirect("loginSuccessTraining.jsp");
		}
		else {
			System.out.println("this file was null");
			response.sendRedirect("loginSuccess.jsp");
		}
		
	}

}
