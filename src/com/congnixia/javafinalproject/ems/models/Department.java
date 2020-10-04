package com.congnixia.javafinalproject.ems.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.congnixia.javafinalproject.ems.filemanipulation.FileMethods;

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
	
	public static int getLastDepartmentId() {
		try {
			return FileMethods.findLastOfDepartmentId() + 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
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
	
	public static List<Department> listDepartments() {
		try {
			return FileMethods.listTheDepartments();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addDepartment(Department d) {
		 return FileMethods.AddTheDepartment(d);
	}

	public static boolean updateDepartment(int index, Department dep) {
		try {
			return FileMethods.updateTheDepartment(index, dep);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean removeTheEmployee(int index) {
		try {
			return FileMethods.removeTheDepartment(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Department findDepartmentByName(String name) {
		return listDepartments().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static List<Employee> findAllEmployeesWorkingInDepartment(Department dep) {
		return Employee.listEmployees().stream().filter(x -> x.getDepartmentId() == dep.getDepartmentId()).collect(Collectors.toList());
	}
	
	public static Department findDepartmentById(int id) {
		return listDepartments().stream().filter(x -> x.getDepartmentId() == id).findFirst().orElse(null);
	}

	/**
	 * Finds the current employee head of the department. Removes them completey. Updates Employee who will take over the department.
	 * 
	 * 
	 * 
	 * @param emp
	 * @param dep
	 * @return
	 */
	public static boolean makeEmployeeHeadOfDepartment(Employee emp, Department dep) {
		// Gets the departments head and removes it
		int currentHeadId = dep.getEmployeeId();
		Employee.removeEmployee(currentHeadId);
		
		// See if the current 
		if(emp.getDepartmentHead()) {
			System.out.println("There is a new department head.!");
			Employee promoted = Employee.findEmployeeToBeNewHeadBySalary();
			promoted.setDepartmentHead(true);
			promoted.setDepartmentId(emp.getDepartmentId());
		}
		emp.setDepartmentHead(true);
		emp.setDepartmentId(dep.getDepartmentId());

		dep.employeeId = emp.getEmployeeId();

		// Employee.
		return true;
	}

	// Find department head gets replace theu are fired and we remove the employee also.

	// If you are a current department head we replace you with another stand in.

	// If the department is deleted they are all placed in a department called the untouchables that you cant leave unless you Take a 25%  pay decrease.

	public static String[] getAllDepartmentNames() {
		return listDepartments().stream().map(x -> x.getName()).toArray(String[]::new);
	}	

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", employeeId=" + employeeId
				+ ", phoneNumberExt=" + phoneNumberExt + ", budget=" + budget + ", allEmployees=" + allEmployees + "]";
	}
	
}
