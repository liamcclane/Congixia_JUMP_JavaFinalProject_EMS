package com.congnixia.javafinalproject.ems.models;

public class Employee {

	// attributes
	transient static int idSeed = 101;

	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String hierDate;
	private Department department;
	private boolean isDepartmentHead;

	// constructor
	public Employee(String name, String email, String phoneNumber, String hierDate, Department d) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.hierDate = hierDate;
		this.id = idSeed;
		this.department = d;
		this.isDepartmentHead = false;
		d.addEmployee(this);
		idSeed++;
	}

	// getters/setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHierDate() {
		return hierDate;
	}
	
	public void setHierDate(String hierDate) {
		this.hierDate = hierDate;
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public boolean isDepartmentHead() {
		return isDepartmentHead;
	}

	public void setIsDepartmentHead(boolean isDepartmentHead) {
		this.isDepartmentHead = isDepartmentHead;
	}

	// to string method
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", phoneNumber=" + phoneNumber + ", department=" + department
				+ "]";
	}

}
