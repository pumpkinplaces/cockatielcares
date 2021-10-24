package com.ccares.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import com.ccares.userinfo.CcaresUser;
import com.ccares.userinfo.UserAuth;
import com.ccares.database.LoginDb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class TokenCreator {

	public static void createNewToken(LoginDb loginDb, CcaresUser user, HttpServletResponse response, String oldSelector, String oldValidator) {
		System.out.println("a new token is created");
    	UserAuth newToken = new UserAuth();
		String selector = RandomStringUtils.randomAlphanumeric(12);
		String rawValidator = RandomStringUtils.randomAlphanumeric(64);
		
		try {
			String hashedValidator = HashGenerator.generateSHA256(rawValidator);
			newToken.setSelector(selector);
			newToken.setValidator(hashedValidator);
			newToken.setCcaresUser(user);
			loginDb.insertUserAuth(newToken, oldSelector, oldValidator);
			
			Cookie selectorCookie = new Cookie("selector", selector);
			selectorCookie.setMaxAge(604800);
			
			Cookie validatorCookie = new Cookie("validator", rawValidator);
			validatorCookie.setMaxAge(604800);
			
			response.addCookie(selectorCookie);
			response.addCookie(validatorCookie);
			
		} catch (HashGeneratorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
