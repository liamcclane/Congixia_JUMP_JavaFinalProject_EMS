package com.congnixia.javafinalproject.ems.filemanipulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.congnixia.javafinalproject.ems.models.*;

public class FileDriver {

	private static List<Object> departments = new ArrayList<Object>();
	private static List<Object> employees = new ArrayList<Object>();
	
	public static void main(String[] args) throws IOException {

		makeTestData();
		
		// ReadingFiles.writeAllToFile(employees);
		// ReadingFiles.writeAllToFile(departments);
		
		// Employee.listEmployees();
		// Department.listDepartments();
	}

	public static void makeTestData() throws IOException {

		departments.add(new Department(Department.getLastDepartmentId(), "Sales", 6, 111, 1000000.00d));
		departments.add(new Department(Department.getLastDepartmentId(), "Electronics", 10, 222, 125000.00d));
		departments.add(new Department(Department.getLastDepartmentId(), "HQ", 11, 333, 1500000.00));
		departments.add(new Department(Department.getLastDepartmentId(), "IT", 12, 444, 375000.00));

		employees.add(new Employee(Employee.getLastEmployeeId(), "Joe", "joe@google.com", "123-555-0000", "1990-08-27", 55000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Bob", "bob@google.com", "123-555-0001", "1991-01-05", 15000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Bill", "bill@google.com", "123-555-0002", "1994-04-16", 25000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Ralph", "ralph@google.com", "123-555-0003", "1995-05-08", 10000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "James", "james@google.com", "123-555-0004", "1998-05-12", 20000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jessica", "jessica@google.com", "123-555-0005", "1999-01-09", 100000.00d, true, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Peter", "peter@google.com", "123-555-0006", "2003-12-09", 90000.00d, false, 1));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Penny", "penny@google.com", "123-555-0007", "2005-08-11", 65000.00d, false, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jenny", "jenny@google.com", "123-555-0008", "2007-14-22", 34000.00d, false, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Ellie", "ellie@google.com", "123-555-0009", "2008-09-21", 76000.00d, true, 2));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jared", "jared@google.com", "123-555-0010", "2010-02-01", 12000.00d, true, 3));
		employees.add(new Employee(Employee.getLastEmployeeId(), "Jordan", "jordan@google.com", "123-555-0011", "2019-08-16", 0.50d, true, 4));
	}
}