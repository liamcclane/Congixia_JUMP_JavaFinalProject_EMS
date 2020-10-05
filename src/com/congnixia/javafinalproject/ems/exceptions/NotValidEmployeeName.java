package com.congnixia.javafinalproject.ems.exceptions;

public class NotValidEmployeeName extends Exception {
	
	
	private static final long serialVersionUID = 8787722634120340847L;
	private String s;
	
	public NotValidEmployeeName(String str) {
		
		super("Input was not a valid employee name");
		
		this.s = str;
	}
	

}


