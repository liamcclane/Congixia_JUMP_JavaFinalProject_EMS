package com.congnixia.javafinalproject.ems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReggieValidation {

	public boolean isValidEmail(String s) {
		String reggie = "[a-zA-z0-9]+@[a-zA-z0-9]+\\.com";
		Pattern pattern = Pattern.compile(reggie);
		Matcher matchy = pattern.matcher(s);
		return matchy.matches();
	}
	
	public boolean isValidPhoneNumber(String s) {
		String reggieAlt = "\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}";
		Pattern patternAlt = Pattern.compile(reggieAlt);
		Matcher matchyAlt = patternAlt.matcher(s);

		String reggie = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
		Pattern pattern = Pattern.compile(reggie);
		Matcher matchy = pattern.matcher(s);
		return matchy.matches() || matchyAlt.matches();
	}
	
	
	
}
