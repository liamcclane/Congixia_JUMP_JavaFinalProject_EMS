package com.cognixia.javafinalproject.ems.models;

public class DepartmentHeadCapacityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DepartmentHeadCapacityException(Employee currentHead) {
		super("You cannot appoint a new Department Head. " + currentHead + "is the current Department Head.");
	}

}
