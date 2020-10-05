package com.congnixia.javafinalproject.ems.filemanipulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.congnixia.javafinalproject.ems.models.*;

public class FileDriver {

	private static List<Department> departments = new ArrayList<Department>();
	private static List<Employee> employees = new ArrayList<Employee>();
	
	public static void main(String[] args) throws IOException {
		FileMethods.runOnce();
		
<<<<<<< HEAD
		// for(String s: Department.getAllDepartmentNames()) {
		// 	System.out.println(s);
		// }
		
		// System.out.println(Department.findAllEmployeesWorkingInDepartment(new Department(1, "Sales", 6, "111", 1000000.00d)));
		
		// System.out.println(Department.findDepartmentByName("HQ"));
		
//		System.out.println(Employee.findEmployeeToBeNewHeadBySalary());
		
//		Employee emp1 = Employee.findEmployeeById(10);
//		Department dep1 = Department.findDepartmentByName("Sales");
//		Department.makeEmployeeHeadOfDepartment(emp1, dep1);
=======
//		for(String s: Department.getAllDepartmentNames()) {
//			System.out.println(s);
//		}

//		System.out.println(Department.findDepartmentById(2));
		
		// for(String s: Department.getAllDepartmentNames()) {
		// 	System.out.println(s);
		// }

		
//		System.out.println(Department.findAllEmployeesWorkingInDepartment(new Department(1, "Sales", 6, "111", 1000000.00d)));
//		
//		System.out.println(Department.findDepartmentByName("HQ"));
		

//		System.out.println(Employee.findEmployeeToBeNewHeadBySalary());
		
//		Employee emp1 = Employee.findEmployeeById(10);
//		Department dep1 = Department.findDepartmentByName("Sales");
//		Department.makeEmployeeHeadOfDepartment(emp1, dep1);
		
//		List<Employee> employeeList = new ArrayList<Employee>();
//		employeeList.add(new Employee(Employee.getLastEmployeeId(), "Person", "1234567890", 45000d, false, 3));
//		employeeList.add(new Employee(Employee.getLastEmployeeId(), "Person", "1234567890", 45000d, false, 3));
//		System.out.println(Employee.listEmployees());
//		System.out.println(Employee.findEmployeeById(1));
//		System.out.println(Employee.findAllEmployeesByName("jared"));
//		System.out.println(Employee.findEmployeeByEmail("joe@google.com"));
////		FileMethods.addEmployeeList(employeeList);
//		System.out.println(Employee.findEmployeeByEmail("ralph"));
//		employeeList.add(new Employee(Employee.getLastEmployeeId(), "Person", Employee.setNewValidEmail("Person"), "3123123123", 55000d, false, 3));
//		employeeList.add(new Employee(Employee.getLastEmployeeId(), "Person", Employee.setNewValidEmail("Person"), "1231231231", 65000d, false, 2));
//		
//		System.out.println(employeeList);
//		System.out.println("TEST");

>>>>>>> daniel-edits
		
		// LIST: 
//		System.out.println("This will list all of the employees: " + Employee.listEmployees());
		// UPDATE:
//		System.out.println("This will return true if an Employee was updated." + Employee.updateEmployee(10, new Employee(10, "Not Man MAN", "Note Bobman@google.com", "324-234-1233", "2029-01-05", 105000.50d, false, 2)));
		// REMOVE:
//		System.out.println("This will return true if an Employee was removed." + Employee.removeEmployee(10));
		// ADD:
//		System.out.println("This will return true if an Employee was added." + Employee.addEmployee(new Employee(Employee.getLastEmployeeId(), "LOL", "LOL@google.com", "324-234-1113", "3029-01-05", 1050200.50d, false, 2)));
		// LIST: 
//		System.out.println("This will list all of the employees: " + Employee.listEmployees());
		// FINDEmployeeById
//		System.out.println("This will return the Employee with the ID of 11: " + Employee.findEmployeeById(11));
//		// LIST ALL EMPLOYEES BY NAME
//		System.out.println("This will list all the employees with the name LOL: " + Employee.findAllEmployeesByName("LOL"));	
//		/// List All Employees in a department.
//		System.out.println("This will list all of the employees in the department: " + Employee.findAllEmployeesByDepartment(new Department(1, "Sales", 6, 111, 1000000.00d)));
	}

	public static List<Department> makeTestDataDepartment() throws IOException {
		departments.add(new Department(Department.getLastDepartmentId(), "Sales", 6, "111", 1000000.00d));
		departments.add(new Department(Department.getLastDepartmentId(), "Electronics", 10, "222", 125000.00d));
		departments.add(new Department(Department.getLastDepartmentId(), "HQ", 11, "333", 1500000.00));
		departments.add(new Department(Department.getLastDepartmentId(), "IT", 12, "444", 375000.00));
	
		return departments;
	}
	
	public static List<Employee> makeTestDataEmployee() throws IOException {
		employees.add(new Employee(Employee.getLastEmployeeId(), "Joe", "something", "joe@google.com", "123-555-0000", 55000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Bob", "some", "bob@google.com", "123-555-0001", 15000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Bill", "somet", "bill@google.com", "123-555-0002", 25000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Ralph", "someth", "ralph@google.com", "123-555-0003", 10000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "James", "somethi", "james@google.com", "123-555-0004", 20000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jessica", "somethin", "jessica@google.com", "123-555-0005", 100000.00d, true, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Peter", "soemthing1", "peter@google.com", "123-555-0006", 90000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Penny", "something22", "penny@google.com", "123-555-0007", 65000.00d, false, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jenny", "somethi", "jenny@google.com", "123-555-0008", 34000.00d, false, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Ellie", "some", "ellie@google.com", "123-555-0009", 76000.00d, true, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jared", "s", "jared@google.com", "123-555-0010", 12000.00d, true, 3));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jordan", "som", "jordan@google.com", "123-555-0011", 0.50d, true, 4));
		
		return employees;
	}
}