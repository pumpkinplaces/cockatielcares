package com.ccares.database;

import com.ccares.email.EmailSender;
import com.ccares.email.EmailUtility;
import com.ccares.userinfo.ProfilePic;
import com.ccares.userinfo.UserAuth;
import com.ccares.userinfo.LoginBean;
import com.ccares.userinfo.CcaresUser;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;
import java.util.regex.*;


public class LoginDb {
	private String dbURL = "jdbc:mysql://localhost:3306/ccaresdb"
			+ "";
	//private String dbURL = "jdbc:mysql://mysql3000.mochahost.com/cockatie_ccaresdb";
	//private String usernm = "cockatie_ccare";
	//private String passwd = "greenstarising";
	private Connection myConn;
	
	//private String dbURL = "jdbc:mysql://localhost:3306/ccaresdb";
	private String usernm = "root";
	private String passwd = "Shhtalklouder1?";
	
	public void setupConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 myConn = DriverManager.getConnection(dbURL, usernm, passwd);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("was unable to setup MyConn");
			e.printStackTrace();
		}
	}

	
	public boolean validatePassword(String username, String password) {
		setupConnection();
		try {
			String sarg = "SELECT PASSWORD FROM tielAccount WHERE username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				if (password.equals(password)) {
					return true;
				}
			} 
			myConn.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean usernameQualifies(String username, ServletContext application) {
		if (usernameExists(username)) {
			String sorry = "Sorry, there is already a user with this name. ";
			String please = "Please choose another name.";
			application.setAttribute("usernameError", sorry + please);
			return false;
		}
		else if (uContainSpace(username)) {
			application.setAttribute("usernameError", "The username cannot have any spaces.");
			return false;
		}
		else if (uNotLongEnough(username)){
			application.setAttribute("usernameError", "The username must be at least 5 characters long.");
			return false;
		}
		else if (unTooLong(username)) {
			application.setAttribute("usernameError",  "The username cannot be longer than 30 characters");
			return false;
		}
		application.removeAttribute("usernameError");
		return true;
	}
	
	public boolean qualifies(LoginBean loginbean) {
		
		String un = loginbean.getUsername();
		
		boolean uNotlongEnough = uNotLongEnough(un);
		
		boolean alreadyExists = usernameExists(un);
		
		boolean hasSpace = uContainSpace(un);
		
		if (alreadyExists) {
			LoginBean.setSomethingswrongJ(true);		
			return false;
		}
		
		if (hasSpace) {
			LoginBean.setSomethingswrongJ(true);
			return false;
		}
		
		if (uNotlongEnough) {
			LoginBean.setSomethingswrongJ(true);
			return false;
		} 	
		return true;
	}

	
	public boolean uNotLongEnough(String un) {
		if (un.length() < 5) {
			return true;
		}
		return false;
	}

	public boolean unTooLong(String un) {
		if (un.length() > 30) {
			return true;
		}
		return false;
	}
	
	public boolean hasSpecialCharacter(String s) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
	}
	
	public boolean hasCapitalLetter(String s) {
		 String regex = "^(?=.*[A-Z]).+$";
		 Pattern p = Pattern.compile(regex);
		 Matcher m = p.matcher(s);
		 return m.matches();
	}
	
	
	public boolean passIsGood(String pw) {
		if (pw == null) {
			return false;
		}
		boolean passPasses= true;
		boolean hasNum = pw.matches(".*\\d.*");
		boolean hasSpecialChar = hasSpecialCharacter(pw);
		boolean hasCapital = hasCapitalLetter(pw);
		boolean hasNumorSpecialChar = (hasNum || hasSpecialChar) && hasCapital;
	
		if (pw.length() < 8) {
			passPasses = false;
		}
		else if (!hasNumorSpecialChar){
			passPasses = false;
		}
		return passPasses;
	}
	
	public void insertElems(LoginBean lb) {
		setupConnection();
		try {
			String username = lb.getUsername();
			String password = lb.getPassword();
			
			
			String sarg = "select * from tielAccount where username = ? AND password = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			
			 if(!rs.next()) {
				String pt1 = "INSERT INTO tielAccount (username, password) ";
				String pt2 = "VALUES ( '" + username.toLowerCase() + "', '" + password + "')";
				String insertArg = pt1 + pt2;
				Statement insSt = myConn.prepareStatement(insertArg);
				insSt.executeUpdate(insertArg);
				myConn.close();
			}
		
		} //End of try 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPfpString(InputStream inputStream) throws IOException {
		String pfp = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		byte[] pfpBytes = outputStream.toByteArray();
		pfp = Base64.getEncoder().encodeToString(pfpBytes);
		
		return pfp;
	}
	
	public String displayPfp(String username) throws IOException {
		setupConnection();
		String sarg = "select PROFILEPIC from tielAccount WHERE username = ?";
		String pfp = "";
		try {
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Blob pfpBlob = rs.getBlob("profilepic");
				if (pfpBlob == null) {
					return null;
				}
				InputStream inputStream = pfpBlob.getBinaryStream();
				pfp = getPfpString(inputStream);
			}
			myConn.close();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pfp;
	}
	
	public void updatePfp(String currentUsername) {
		setupConnection();
		try {
			InputStream inputStream = ProfilePic.inputStream;
			String sarg = "UPDATE tielAccount SET profilepic = ? WHERE username = \"" + currentUsername + "\"";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setBlob(1, inputStream);
			st.executeUpdate();
			myConn.close();
		
		} //End of try 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEmailAddr(String username, String emailAd) {
		
		setupConnection();
		try {
			String sarg = "UPDATE tielAccount SET email = ? WHERE username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, emailAd.toLowerCase());
			st.setString(2, username);
			st.executeUpdate();		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean usernameExists(String un) { 
		boolean alreadyExists = false;
		setupConnection();
		try {
			String sarg = "select * from tielAccount where username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, un);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				alreadyExists = true;	
			}
			myConn.close();
		} //End of try 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alreadyExists;
	}
	
	public void emailAlreadyExists(String emailAd) {
		setupConnection();
		
		String sarg = "select * from tielAccount where email = ?";
		try {
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, emailAd);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				EmailUtility.emailAlreadyExists = true;}
			else { 
				EmailUtility.emailAlreadyExists = false;}
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean uContainSpace(String un) {
		boolean hasSpace = false;
		if (un.contains(" ")) {
			hasSpace = true;
		}
		return hasSpace;
	}
	
	public boolean userDNE(String un, ResultSet rs) throws SQLException {
		boolean dne = false;
		if (!rs.next()) {
			dne = true;
			String errormessage = "A user with this name does not exist.";
			LoginBean.setUserdneJ(errormessage);	
			
		}
		return dne;
	}

	public String getEmail(String username) {
		setupConnection();
		String email = "";
		String sarg = "select EMAIL from tielAccount WHERE username = ?";
		try {
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				email = rs.getString("email");
			}
			myConn.close();
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return email;
	}
	
	public boolean exists(String username, String column) {
		boolean exists = false;
		try {
			String sarg =  "select * from tielAccount where " + column + " = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				exists = true;
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
		
	}
	
	public void updateAll(String username, String newUsername, String email, HttpServletRequest request) {
		
		setupConnection();
		HttpSession session = request.getSession(false);
		CcaresUser user = (CcaresUser) session.getAttribute("user");
		String realEmail = user.getEmail();
		CcaresUser fakeuser = new CcaresUser();
		if (newUsername != null) {
			if (exists(newUsername, "username") && !username.equalsIgnoreCase(newUsername)) {
				session.setAttribute("errormessage", "Sorry, but someone else is already using this username!");
				fakeuser.setUsername(newUsername.toLowerCase());
			}
			else {
				session.removeAttribute("errormessage");
				updateUsername(username, newUsername);
				user.setUsername(newUsername.toLowerCase());
			}
		}
		if (email != null) {
			if (exists(email, "email") && !email.equalsIgnoreCase(realEmail)) {
				session.setAttribute("emailerror", "Sorry, this email address is already taken.");
				fakeuser.setEmail(email.toLowerCase());
			}
			else if (!EmailSender.isValid(email)) {
				session.setAttribute("emailerror", "Please enter a valid email address.");
				fakeuser.setEmail(email.toLowerCase());
			}
			else {
				session.removeAttribute("emailerror");
				updateEmail(email, username);
				user.setEmail(email.toLowerCase());
			}
		}
		try {myConn.close();}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		session.setAttribute("fakeuser", fakeuser);
	}
	
	public void updateUsername(String username, String newUsername) {
		try {
			String sarg = "UPDATE tielAccount SET username = ? WHERE username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);	
			st.setString(1, newUsername.toLowerCase());
			st.setString(2, username);
			st.executeUpdate();	
		} //End of try 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEmail(String email, String username) {
		try {
			String sarg = "UPDATE tielAccount SET email = ? WHERE username = \"" + username + "\"";
			PreparedStatement st = myConn.prepareStatement(sarg);	
			st.setString(1, email.toLowerCase());
			st.executeUpdate();
		} //End of try 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String username, String password) {
		setupConnection();
		try {
			String sarg = "UPDATE tielAccount SET password = ? WHERE username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, password);
			st.setString(2, username);
			st.executeUpdate();
			myConn.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getID(String username) {
		int id = 0; 
		try {
			String sarg = "SELECT * FROM tielAccount WHERE username = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public int userInTokenDb(int tiel_authId) throws SQLException {
		String sarg = "SELECT * FROM tiel_auth WHERE EXISTS (SELECT 1 FROM tielAccount WHERE tielAccount.id = tiel_auth.user_id AND tiel_auth.id = ?)";
		PreparedStatement st = myConn.prepareStatement(sarg);
		st.setLong(1, tiel_authId);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			return rs.getInt("id");	
		}
		return -1;
	}
	
	public int getTielAuthID(String selector, String validator) {
		int id = -1;
		try {
			String sarg = "SELECT * from tiel_auth WHERE selector = ? AND validator = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, selector);
			st.setString(2, validator);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void insertUserAuth(UserAuth token, String oldSelector, String oldValidator) {
		setupConnection();
		try {
			CcaresUser user = token.getCcaresUser();
			int id = this.getID(user.getUsername());
			String selector = token.getSelector();
			String validator = token.getValidator();
			int tielAuthID = getTielAuthID(oldSelector, oldValidator);
			if ((userInTokenDb(tielAuthID)) != -1) {
				String sarg = "UPDATE tiel_auth SET selector = ?, validator = ? WHERE user_id = ?";
				PreparedStatement st = myConn.prepareStatement(sarg);
				st.setString(1, selector);
				st.setString(2, validator);
				st.setInt(3, id);
				st.executeUpdate();
				return;
			}
			System.out.println("its going to do the insert");
			String pt1 = "INSERT INTO tiel_auth (selector, validator, user_id) ";
			String pt2 = "VALUES ('" + selector + "', '" + validator + "', '" + id + "')";
	
			String arg = pt1 + pt2;
			PreparedStatement st = myConn.prepareStatement(arg);
			st.executeUpdate(arg);
			myConn.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CcaresUser getUserFromUserAuth(int id) throws IOException {
		CcaresUser user = new CcaresUser();
		String sarg = "SELECT * from tielAccount WHERE id = ?";
		try {
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				String base64Pfp = this.displayPfp(rs.getString("username"));
				user.setProfilePic(base64Pfp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public CcaresUser findBySelector(String selector, String hashedValidator) throws IOException {
		setupConnection();
		CcaresUser user = null;
		try {
			String arg = "SELECT * FROM tiel_auth WHERE selector = ? AND validator = ?";
			PreparedStatement st = myConn.prepareStatement(arg);
			st.setString(1, selector);
			st.setString(2, hashedValidator);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int user_id = rs.getInt("user_id");
				user = getUserFromUserAuth(user_id);
			}
			myConn.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public void removeToken(String selector, String hashedValidator) {
		setupConnection();
		try {
			String sarg = "DELETE FROM tiel_auth WHERE selector = ? AND validator = ?";
			PreparedStatement st = myConn.prepareStatement(sarg);
			st.setString(1, selector);
			st.setString(2, hashedValidator);
			st.executeUpdate();
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
} //end of class

