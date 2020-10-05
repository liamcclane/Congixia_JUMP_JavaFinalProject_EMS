package com.congnixia.javafinalproject.ems.exceptions;

public class NotValidDepartmentOption extends Exception {
	
	
	private static final long serialVersionUID = -7786691408541147113L;
	
	private String s;
	
	public NotValidDepartmentOption(String str) {
		
		super("Input was not vaild");
		
		this.s = str;
	}
	

}

