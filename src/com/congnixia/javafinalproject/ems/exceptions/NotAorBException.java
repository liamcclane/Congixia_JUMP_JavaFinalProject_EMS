package com.congnixia.javafinalproject.ems.exceptions;

public class NotAorBException extends Exception {

	private static final long serialVersionUID = -159222L;
	
	public final static String A = "a";
	public final static String B = "b";
	
	private String s;
	
	public NotAorBException(String str) {
		
		super("This was neither A or B");
		
		this.s = str;
		
	}
	
	
}
