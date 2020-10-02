package com.congnixia.javafinalproject.ems.exceptions;

public class NotTorFException extends Exception {
	
	
	private static final long serialVersionUID = -662846027123126846L;
	public final static String T = "t";
	public final static String F = "f";
	
	private String s;
	
	public NotTorFException(String str) {
		
		super("Input was neither T or Y");
		
		this.s = str;
	}
	

}