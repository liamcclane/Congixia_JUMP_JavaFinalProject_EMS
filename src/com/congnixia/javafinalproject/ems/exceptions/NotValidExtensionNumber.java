package com.congnixia.javafinalproject.ems.exceptions;


public class NotValidExtensionNumber extends Exception{

	private static final long serialVersionUID = -351010885936027317L;
	
	private static String s;
	
	public NotValidExtensionNumber(String str) {
		
		super("You must input a three-digit number.");
		
		s = str;
	}
}
