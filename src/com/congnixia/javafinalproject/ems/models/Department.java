package com.congnixia.javafinalproject.ems.models;

import java.util.ArrayList;
import java.util.List;

public class Department {

	// attributes
	private Employee departmentHead;
	private String type; // HR, Sales, Electronics, GeekSquad,
	private int phoneNumberExt;
	private double budget;
	// I feel like the problem statement wants this... 
	private List<Employee> allEmployees = new ArrayList<>();
	

	// constructors
	/**
	 * departments are made first!, then employees
	 * @param type
	 * @param phoneNumberExt
	 * @param budget
	 */
	public Department(String type, int phoneNumberExt, double budget) {
		super();
		this.type = type;
		this.phoneNumberExt = phoneNumberExt;
		this.budget = budget;
	}

	public Employee getDepartmentHead() {
		return departmentHead;
	}

	// getters/setters
	/**
	 * pass in the employee you want as the department head
	 * @param departmentHead
	 */
	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
		departmentHead.setIsDepartmentHead(true);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPhoneNumberExt() {
		return phoneNumberExt;
	}

	public void setPhoneNumberExt(int phoneNumberExt) {
		this.phoneNumberExt = phoneNumberExt;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	
	@Override
	public String toString() {
		return "Department [departmentHead=" + departmentHead + ", type=" + type + ", phoneNumberExt=" + phoneNumberExt
				+ ", budget=" + budget + ", allEmployees=" + allEmployees + "]";
	}
	// could you maybe make a nicer looking print statement for 
	// listing all the employees in a department, please and thank you  :)

	// MORE METHODS WITH THE EMPLOYEE ARRAYLIST
	public boolean addEmployee(Employee employee) {
		return true; // true if they were added, false if not
	}
	public boolean removeEmployee(Employee employee) {
		return true; // true if they were added, false if not
	}
	public boolean replaceEmployee(Employee employee) {
		return true; // true if they were added, false if not
	}

}
