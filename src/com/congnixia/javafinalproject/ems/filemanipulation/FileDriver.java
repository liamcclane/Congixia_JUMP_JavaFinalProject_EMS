package com.congnixia.javafinalproject.ems.filemanipulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.congnixia.javafinalproject.ems.models.*;

public class FileDriver {

	private static List<Department> departments = new ArrayList<Department>();
	private static List<Employee> employees = new ArrayList<Employee>();
	
	public static void main(String[] args) throws IOException {

		makeTestData();
		
		Employee emp = new Employee(Employee.getLastEmployeeId(), "Joe", "joe@google.com", "123-555-0000", "1990-08-27", 55000.00d, false, 1);
		try {
			Employee.addEmployee(emp);
			Employee.listEmployees();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void makeTestData() {

		departments.add(new Department(1, "Sales", 6, 111, 1000000.00d));
		departments.add(new Department(2, "Electronics", 10, 222, 125000.00d));
		departments.add(new Department(3, "HQ", 11, 333, 1500000.00));
		departments.add(new Department(4, "IT", 12, 444, 375000.00));

		employees.add(new Employee(1, "Joe", "joe@google.com", "123-555-0000", "1990-08-27", 55000.00d, false, 1));
		employees.add(new Employee(2, "Bob", "bob@google.com", "123-555-0001", "1991-01-05", 15000.00d, false, 1));
		employees.add(new Employee(3, "Bill", "bill@google.com", "123-555-0002", "1994-04-16", 25000.00d, false, 1));
		employees.add(new Employee(4, "Ralph", "ralph@google.com", "123-555-0003", "1995-05-08", 10000.00d, false, 1));
		employees.add(new Employee(5, "James", "james@google.com", "123-555-0004", "1998-05-12", 20000.00d, false, 1));
		employees.add(new Employee(6, "Jessica", "jessica@google.com", "123-555-0005", "1999-01-09", 100000.00d, true, 1));
		employees.add(new Employee(7, "Peter", "peter@google.com", "123-555-0006", "2003-12-09", 90000.00d, false, 1));
		employees.add(new Employee(8, "Penny", "penny@google.com", "123-555-0007", "2005-08-11", 65000.00d, false, 2));
		employees.add(new Employee(9, "Jenny", "jenny@google.com", "123-555-0008", "2007-14-22", 34000.00d, false, 2));
		employees.add(new Employee(10, "Ellie", "ellie@google.com", "123-555-0009", "2008-09-21", 76000.00d, true, 2));
		employees.add(new Employee(11, "Jared", "jared@google.com", "123-555-0010", "2010-02-01", 12000.00d, true, 3));
		employees.add(new Employee(12, "Jordan", "jordan@google.com", "123-555-0011", "2019-08-16", 0.50d, true, 4));
	}
}