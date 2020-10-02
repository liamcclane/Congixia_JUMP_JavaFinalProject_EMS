package com.congnixia.javafinalproject.ems.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.congnixia.javafinalproject.ems.filemanipulation.FileMethods;
import com.congnixia.javafinalproject.ems.filemanipulation.ReadingFiles;

public class Department {

	
	private int departmentId;
	private String name;
	private int employeeId;
	private String phoneNumberExt;
	private double budget;
	
	private final List<Employee> allEmployees = new ArrayList<>();

	public Department(int departmentId, String name, int employeeId, String phoneNumberExt, double budget) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.employeeId = employeeId;
		this.phoneNumberExt = phoneNumberExt;
		this.budget = budget;
	}
	
	public static int getLastDepartmentId() throws IOException {
		return FileMethods.findLastOfDepartmentId() + 1;
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

	public String getPhoneNumberExt() {
		return phoneNumberExt;
	}

	public void setPhoneNumberExt(String phoneNumberExt) {
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
	
	public static List<Department> listDepartments() throws IOException {
		return FileMethods.listTheDepartments();
	}

	public static boolean addDepartment(Department d) throws IOException {
		 return FileMethods.AddTheDepartment(d);
	}

	public static boolean updateDepartment(int index, Department dep) throws IOException {
		return FileMethods.updateTheDepartment(index, dep);
	}

	public static boolean removeTheEmployee(int index) throws IOException {
		return FileMethods.removeTheDepartment(index);
	}

	public static Department findDepartmentByName(String name) throws IOException {
		return listDepartments().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static List<Employee> findAllEmployeesWorkingInDepartment(Department dep) throws IOException {
		return Employee.listEmployees().stream().filter(x -> x.getDepartmentId() == dep.getDepartmentId()).collect(Collectors.toList());
	}

	public static boolean makeEmployeeHeadOfDepartment(Employee emp, Department dep) {
		// Read in employee find current id
		
		return true;
	}

	// Find department head gets replace theu are fired and we remove the employee also.

	// If you are a current department head we replace you with another stand in.

	// If the department is deleted they are all placed in a department called the untouchables that you cant leave unless you Take a 25%  pay decrease.

	public static String[] getAllDepartmentNames() throws IOException {
		return listDepartments().stream().map(x -> x.getName()).toArray(String[]::new);
	}


	/*
	findDepartmentByName() returns Department
findAllEmployeesWorkingInDepartment(Department department) returns ArrayList<Employee>
makeEmployeeHeadOfDepartment(Employee employee, Department department) return boolean
getAllDepartmentNemes() returns String[]
	*/
	
	

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", employeeId=" + employeeId
				+ ", phoneNumberExt=" + phoneNumberExt + ", budget=" + budget + ", allEmployees=" + allEmployees + "]";
	}
	
}
