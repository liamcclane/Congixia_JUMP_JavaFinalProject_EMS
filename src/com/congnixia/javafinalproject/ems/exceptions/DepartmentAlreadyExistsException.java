package com.congnixia.javafinalproject.ems.exceptions;

public class DepartmentAlreadyExistsException extends Exception {

	
	private static final long serialVersionUID = -778147113L;
	
	private String s;
	
	public DepartmentAlreadyExistsException(String str) {
		
		super("That department already exists");
		this.s = str;
	}
	
}
