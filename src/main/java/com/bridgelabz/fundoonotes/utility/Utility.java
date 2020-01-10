package com.bridgelabz.fundoonotes.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility
{
	static BCryptPasswordEncoder bc= new BCryptPasswordEncoder();
	public static String encoder(String str)
	{
		return bc.encode(str);
	}
	 public static boolean passwordMatcher(String pas,String enPas) {
		 return bc.matches(pas, enPas);
	 }
	
	 
	 
}
