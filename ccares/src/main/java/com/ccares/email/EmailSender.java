package com.ccares.email;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

import com.ccares.database.LoginDb;
import com.ccares.userinfo.CcaresUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class EmailSender
 */

@WebServlet("/EmailSender")
public class EmailSender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String port;
	private String user;
	private String pass;

    /**
     * Default constructor. 
     */
    public EmailSender() {
        // TODO Auto-generated constructor stub
    	// reads SMTP server setting from web.xml file
  
    }
    
    @Override
    public void init() {    	
    	//ServletContext context = getServletContext();	
        //host = context.getInitParameter("host");
        //port = context.getInitParameter("port");
        //user = context.getInitParameter("user");
        //pass = context.getInitParameter("pass");
    	host = "smtp.gmail.com";
    	port = "587";
    	user = "main@cockatielcares.com";
    	pass = "pokiespeakerbutton";
    	//pass = "pokiespeakerbuttons";
    	//host = "mocha6007.mochahost.com";
    	//host = "smtp.gmail.com";
    	//port = "587";
    	//user = "help@cockatielcares.com";
    	//pass = "bar!ofStairs25";
    }
    
    private int generateRandomNumber() {
    	Random randNum = new Random();
    	int max = 99999;
    	int min = 10000;
    	return randNum.nextInt(max - min + 1) + min;
    }
    
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
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
        // reads form fields
		ServletContext application = getServletContext();
		CcaresUser person = (CcaresUser) application.getAttribute("passworduser");
		boolean nullPassUser = person == null;
		CcaresUser almostUser = (CcaresUser) application.getAttribute("almostUser");
		boolean nullProcess = almostUser == null;
		boolean bothNull = nullPassUser && nullProcess;
		if (bothNull) {
			response.sendRedirect("login.jsp");
			return;
		}
		String recipient = request.getParameter("uncoveredemail");
		String newPage = "emailPChange.jsp";
		
		
		if (nullPassUser) {
			recipient = request.getParameter("emailaddr");
			newPage = "verifyEmail.jsp";
			LoginDb ldb = new LoginDb();
			String errorMessage = "";
			almostUser.setEmail(recipient.toLowerCase());
			application.setAttribute("almostUser", almostUser);
			ldb.emailAlreadyExists(recipient);
			if (EmailUtility.emailAlreadyExists) {
	        	errorMessage = "Sorry, this email address is already in use. Please try a different one!";
	        	application.setAttribute("emailError", errorMessage);
	        	response.sendRedirect("makeEmail.jsp");
	        	return;
	        }
			if (!isValid(recipient)) {
				errorMessage = "Sorry, this email address is invalid. Please try a different one!";
				application.setAttribute("emailError", errorMessage);
	        	response.sendRedirect("verifyEmail.jsp");
	        	return;
			}
			application.setAttribute("canTestEmail", true);
		} 
		else {
			String sentEmail = request.getParameter("uncoveredemail");
			person = (CcaresUser) application.getAttribute("passworduser");
			String email = person.getEmail();
			if (sentEmail.equalsIgnoreCase(email)) {
				application.setAttribute("passwordProcess", true);
				application.setAttribute("usernameExists", true);
				application.removeAttribute("sentEmail");
			}
			else {
				application.setAttribute("passwordProcess", true);
				application.setAttribute("usernameExists", true);
				application.setAttribute("emailConfirmationError", "Sorry, you did not enter in the correct email address!");
				application.setAttribute("sentEmail", sentEmail);
				response.sendRedirect("forgotPassword.jsp");
				return;
			}
		}
		
		int num = generateRandomNumber();
        EmailUtility.verificationCode = num;
        String subject = "Cockatiel Cares";
        String content = "This is your verification code: " + num;
   
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            System.out.println("is about to be done wiht try");
            response.sendRedirect(newPage);
        } catch (Exception ex) {
        	ex.printStackTrace();
            System.out.println("uh oh it went to the catch");
            String err = ex.toString();
            request.setAttribute("erro", err);
            request.setAttribute("anothererror", EmailUtility.words);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
	        requestDispatcher.forward(request, response); 
        } finally {
        	application.removeAttribute("emailError");
           
		}
	}
}


