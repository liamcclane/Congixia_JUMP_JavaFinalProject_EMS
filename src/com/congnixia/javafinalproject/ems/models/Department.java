package com.congnixia.javafinalproject.ems.models;

import java.util.ArrayList;
import java.util.List;

public class Department {

	
	private int departmentId;
	private String name;
	private int employeeId;
	private int phoneNumberExt;
	private double budget;
	
	private final List<Employee> allEmployees = new ArrayList<>();

	public Department(int departmentId, String name, int employeeId, int phoneNumberExt, double budget) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.employeeId = employeeId;
		this.phoneNumberExt = phoneNumberExt;
		this.budget = budget;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public List<Employee> getAllEmployees() {
		return allEmployees;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", employeeId=" + employeeId
				+ ", phoneNumberExt=" + phoneNumberExt + ", budget=" + budget + ", allEmployees=" + allEmployees + "]";
	}
	
}
