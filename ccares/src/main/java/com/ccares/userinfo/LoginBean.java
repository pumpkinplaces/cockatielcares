package com.ccares.userinfo;

public class LoginBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	 private String username = "";
	 private String password = "";
	 private static String ptooshort = "";
	 private static String unotlongenough = "";
	 private static String utoolong = "";
	 private static String pwnonumspchars = "";
	 private static boolean wasclicked = false;
	 private static String prevusername = "";
	 private static String prevpassword = "";
	 private static boolean somethingswrong = false;
	 private static String useralreadyexists = "";
	 private static String uspace = "";
	 private static boolean somethingswronglogin = false;
	 private static boolean wasclickedlogin = false;
	 private static String prevusernamelogin =  "";
	 private static String prevpasswordlogin = "";
	 private static String userdne = "";
	 private static String wrongpassword = "";
	 private static String empty = "";
	 private static String currentusername  = "";
	 private static boolean sacwasclicked = false;
	 private static boolean pfpempty = true;
	 private static String baseimage;
	 
	 
	 public LoginBean() {
	   }
	 
	 public String getPtooshort(){
	      return ptooshort;
	   }
	 public String getUsername(){
	      return username;
	   }
	 
	 public void setUsername(String username) {
		 this.username = username;
	 }
	 
	 public String getPassword(){
	      return password;
	   }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 public String getUnotlongenough(){
	      return unotlongenough;
	   }
	 
	 public void setUnotlongenough(String s) {
		 unotlongenough = s;
	 }
	 
	 public String getUtoolong(){
	      return utoolong;
	   }
	 
	 public void setUtoolong(String st) {
		 utoolong = st;
	 }
	 
	 public void setPwtooshort(String s) {
		 ptooshort = s;
	 }
	 
	 public String getPwnonumspchars(){
	      return pwnonumspchars;
	   }
	 
	 public boolean getWasclicked() {
		 return wasclicked;
	 }
	 
	 public void setWasclicked(boolean tf) {
		 wasclicked = tf;
	 }
	 
	 public String getPrevusername() {
		 return prevusername;
	 }
	 
	 public void setPrevusername(String s) {
		  prevusername = s;
	 }
	 
	 public String getPrevpassword() {
		 return prevpassword;
	 }
	 
	 public void setPrevpassword(String s) {
		 prevpassword = s;
	 }
	 
	 public boolean getSomethingswrong() {
		 return somethingswrong;
	 }
	 
	 public void setSomethingswrong(boolean b) {
		 somethingswrong = b;
	 }
	 
	 public String getUseralreadyexists() {
		 return useralreadyexists;
	 }
	 
	 public void settUseralreadyexists(String s) {
		 useralreadyexists = s;
	 }
	 
	 public String getUspace() {
		 return uspace;
	 }
	 
	 public void setUspace(String s) {
		 uspace = s;
	 }
	 
	 public boolean getSomethingswronglogin() {
		 return somethingswronglogin;
	 }
	 
	 public void setSomethingswronglogin(boolean b) {
		 somethingswronglogin = b;
	 }
	 
	 public boolean getWasclickedlogin() {
		 return wasclickedlogin;
	 }
	 
	 public void setWasclickedlogin(boolean b) {
		 wasclickedlogin = b;
	 }
	 
	 public String getPrevusernamelogin() {
		 return prevusernamelogin;
	 }
	 
	 public void setPrevusernamelogin(String s) {
		  prevusernamelogin = s;
	 }
	 
	 public String getPrevpasswordlogin() {
		 return prevpasswordlogin;
	 }
	 
	 public void setPrevpasswordlogin(String s) {
		 prevpasswordlogin = s;
	 }
	 
	 public String getUserdne() {
		 return userdne;
	}
	 
	public void setUserdne(String s) {
		userdne = s;
	}
	
	public String getWrongpassword() {
		return wrongpassword;
	}
	 
	public void setWrongpassword(String s) {
		wrongpassword = s;
	}
	
	public String getCurrentusername() {
		return currentusername;
	}
	 
	public void setCurrentusername(String s) {
		currentusername = s;
	}
	
	public boolean getSacwasclicked() {
		return sacwasclicked;
	}
	 
	public void setSacwasclicked(boolean b) {
		sacwasclicked = b;
	}
	
	public boolean getPfpempty() {
		return pfpempty;
	}
	 
	public void setPfpempty(boolean b) {
		pfpempty = b;
	}
	
	public String getBaseimage() {
		return baseimage;
	}
	 
	public void setBaseiamge(String s) {
		baseimage = s;
	}
	
	 
	 //** This part is for java functions.*/
	 
	 public String getPtooshortJ(){
	      return ptooshort;
	   }
	 public String getUsernameJ(){
	      return username;
	   }
	 
	 public void setUsernameJ(String username) {
		 this.username = username;
	 }
	 
	 public String getPasswordJ(){
	      return password;
	   }
	 
	 public void setPasswordJ(String password) {
		 this.password = password;
	 }
	 
	 public String getUNotlongenoughJ(){
	      return unotlongenough;
	   }
	 
	 public static void setUnotlongenoughJ(String s) {
		 unotlongenough = s;
	 }
	 
	 public static String getUtoolongJ(){
	      return utoolong;
	   }
	 
	 public static void setUtoolongJ(String st) {
		 utoolong = st;
	 }
	 
	 public static void setPwtooshortJ(String s) {
		 ptooshort = s;
	 }
	 
	 public static String getPwnonumspcharsJ(){
	      return pwnonumspchars;
	   }
	 
	 public static void setPwnonumspcharsJ(String s){
	       pwnonumspchars = s;
	   }
	 
	 public static boolean getWasclickedJ() {
		 return wasclicked;
	 }
	 
	 public static void setWasclickedJ(boolean tf) {
		 wasclicked = tf;
	 }
	 
	 public  static String getPrevusernameJ() {
		 return prevusername;
	 }
	 
	 public static void setPrevusernameJ(String s) {
		  prevusername = s;
	 }
	 
	 public static void setPrevpasswordJ(String s) {
		 prevpassword = s;
	 }
	 
	 public  static String getPrevpasswordJ(String s) {
		 return prevpassword;
	 }
	 
	 public static  boolean getSomethingswrongJ() {
		 return somethingswrong;
	 }
	 
	 public static void setSomethingswrongJ(boolean b) {
		 somethingswrong = b;
	 }
	 
	 public static String getUseralreadyexistsJ() {
		 return useralreadyexists;
	 }
	 
	 public static void setUseralreadyexistsJ(String s) {
		 useralreadyexists = s;
	 }
	 
	 public static String getUspaceJ() {
		 return uspace;
	 }
	 
	 public static void setUspaceJ(String s) {
		 uspace = s;
	 }
	 
	 public static boolean getSomethingswrongloginJ() {
		 return somethingswronglogin;
	 }
	 
	 public static void setSomethingswrongloginJ(boolean b) {
		 somethingswronglogin = b;
	 }
	 
	 public static boolean getWasclickedloginJ() {
		 return wasclickedlogin;
	 }
	 
	 public static void setWasclickedloginJ(boolean b) {
		 wasclickedlogin = b;
	 }

	 public static String getPrevusernameloginJ() {
		 return prevusernamelogin;
	 }
	 
	 public static void setPrevusernameloginJ(String s) {
		  prevusernamelogin = s;
	 }
	 
	 public static String getPrevpasswordloginJ() {
		 return prevpasswordlogin;
	 }
	 
	 public static void setPrevpasswordloginJ(String s) {
		 prevpasswordlogin = s;
	 }
	 
	 public String getUserdneJ() {
		 return userdne;
	}
	 
	public static void setUserdneJ(String s) {
		userdne = s;
	}
	
	public static String getWrongpasswordJ() {
		return wrongpassword;
	}
	 
	public static void setWrongpasswordJ(String s) {
		wrongpassword = s;
	}
	
	public static String getCurrentusernameJ() {
		return currentusername;
	}
	 
	public static void setCurrentusernameJ(String s) {
		currentusername = s;
	}
	
	public static boolean getSacwasclickedJ() {
		return sacwasclicked;
	}
	 
	public static void setSacwasclickedJ(boolean b) {
		sacwasclicked = b;
	}
	
	public static boolean getPfpemptyJ() {
		return pfpempty;
	}
	 
	public static void setPfpemptyJ(boolean b) {
		pfpempty = b;
	}
	
	public static String getBaseimageJ() {
		return baseimage;
	}
	 
	public static void setBaseiamgeJ(String s) {
		baseimage = s;
	}
	
	public static void emptyBean() {
		LoginBean.setUnotlongenoughJ(empty);
		LoginBean.setUtoolongJ(empty);
		LoginBean.setPwtooshortJ(empty);
		LoginBean.setPwnonumspcharsJ(empty);
		LoginBean.setUseralreadyexistsJ(empty);
		LoginBean.setUspaceJ(empty);
		LoginBean.setUserdneJ(empty);
		LoginBean.setWrongpasswordJ(empty);
	//	LoginBean.setPrevusernameJ(empty);
	//	LoginBean.setPrevpasswordJ(empty);
	//	LoginBean.setPrevusernameloginJ(empty);
	//	LoginBean.setPrevpasswordJ(empty);
	}

}
