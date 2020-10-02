package com.congnixia.javafinalproject.ems.models;

import java.io.IOException;

import com.congnixia.javafinalproject.ems.filemanipulation.ReadingFiles;

//import com.cognixia.jump.advancedjava.projects.ReadingFiles;

public class Employee {

	private int employeeId;
	private String name;
	private String email;
	private String phoneNumber;
	private String hireDate;
	private double salary;
	private boolean isDepartmentHead;
	private int departmentId;

	public Employee(int employeeId, String name, String email, String phoneNumber, String hireDate, double salary,
			boolean isDepartmentHead, int departmentId) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.salary = salary;
		this.isDepartmentHead = isDepartmentHead;
		this.departmentId = departmentId;
	}

	public static int getLastEmployeeId() throws IOException {
		return ReadingFiles.findLastOfEmployeeId() + 1;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isDepartmentHead() {
		return isDepartmentHead;
	}

	public void setDepartmentHead(boolean isDepartmentHead) {
		this.isDepartmentHead = isDepartmentHead;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public static void listEmployees() throws IOException {
		System.out.println("Employee List:");
		System.out.println(ReadingFiles.readEmployees().toString());
	}

	public static boolean addEmployee(Employee e) throws IOException {
		ReadingFiles.writeToFile(e);
		System.out.println("Employee Added.");
		return true;
	}

	public static boolean updateEmployee() {
		System.out.println("Employee Updated.");
		return true;
	}

	public static boolean removeEmployee() {
		System.out.println("Employee Removed.");
		return true;
	}

	/**
	 * call this method if you want a pretty multi line print
	 * 
	 * @return
	 */
	public Employee prettyPrintln() {
		System.out.println("Name : " + this.name + " \t" + this.employeeId + " : id" + "\nEmail: " + this.email
				+ "\nPhone Number : " + this.phoneNumber);

		return this;
	}
	
	/**
	 * call this method when you want a single line print
	 * @return
	 */
	public Employee prettyPrint() {
		System.out.println("Name : " + this.name + " \t" + this.employeeId + " : id" + "\tEmail: " + this.email
				+ "\tPhone Number : " + this.phoneNumber);

		return this;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary + ", isDepartmentHead="
				+ isDepartmentHead + ", departmentId=" + departmentId + "]";
	}

}
