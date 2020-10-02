package com.congnixia.javafinalproject.ems.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.congnixia.javafinalproject.ems.filemanipulation.FileMethods;

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
	
	/**
	 * This uses the FileMethod's findLastOfEmployeeId which looks through the file and finds the highest id and adds 1.
	 * 
	 * @return int
	 * @throws IOException
	 */
	public static int getLastEmployeeId() throws IOException {
		return FileMethods.findLastOfEmployeeId() + 1;
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

	/**
	 * Returns a List<Employee> because all the FileMethod Classes methods returns a List<Object>
	 * 
	 * 
	 * @return List<Employee>
	 * @throws IOException
	 */
	public static List<Employee> listEmployees() throws IOException {
		List<Employee> employeeList = new ArrayList<Employee>();
		List<Object> objs = FileMethods.listObjects('e');
		for(int i = 0; i < objs.size(); i++) {
			Employee emp = (Employee) objs.get(i);
			employeeList.add(emp);
		}
		return employeeList;
	}

	public static boolean addEmployee(Employee e) throws IOException {
		return FileMethods.addObject(e);
	}
	
	public static boolean updateEmployee(int index, Object obj) throws IOException {
		return FileMethods.updateObject(index, 'e', obj);
	}
	
	public static boolean removeEmployee(int index) throws IOException {
		return FileMethods.removeObject(index, 'e');
	}
	
	public static List<Employee> findAllEmployeesByName(String searchName) throws IOException {
		return listEmployees().stream()
				.filter(x -> x.getName().equals(searchName))
				.collect(Collectors.toList());
	}
	
	public static List<Employee> findAllEmployeesByDepartment(Department department) throws IOException {
		return listEmployees().stream()
				.filter(x -> x.departmentId == department.getDepartmentId())
				.collect(Collectors.toList());
	}
	
	public static Employee findEmployeeByPhoneNumber(String searchNumber) throws IOException {
		return listEmployees().stream()
				.filter(x -> x.getPhoneNumber() == searchNumber)
				.findFirst().get();
	}
			
	public static Employee findEmployeeById(int id) throws IOException {
		return listEmployees().stream()
				.filter(x -> x.getEmployeeId() == id)
				.findFirst().get();
	}

	@Override
	public String toString() {
		return "\n[employeeId=" + employeeId + ",\tname=" + name + ",\temail=" + email + ",    \tphoneNumber="
				+ phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary + ",\tisDepartmentHead="
				+ isDepartmentHead + ",\tdepartmentId=" + departmentId + "]";
	}

}
