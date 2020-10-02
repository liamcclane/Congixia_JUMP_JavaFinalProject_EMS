package com.congnixia.javafinalproject.ems.exceptions;

public class NotYesOrNoException extends Exception {
	
	private static final long serialVersionUID = -161607434598689699L;
	public final static String Y = "y";
	public final static String N = "n";
	
	private String s;
	
	public NotYesOrNoException(String str) {
		
		super("Input was neither Y or N");
		
		this.s = str;
	}
	

}
