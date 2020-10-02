package com.congnixia.javafinalproject.ems;

import java.util.ArrayList;
import java.util.Scanner;

import com.congnixia.javafinalproject.ems.models.*;

public class Demo {

	// lia's branch
	static Scanner scanny = new Scanner(System.in);

	static Department dummyDepartment = new Department(0, "ExDep", 111, 0, 3000.00d);
	static Employee dummyEmployee = new Employee(-1, "fake", "fake@fake.com", "000-000-000", "1/1/11", 1000d, false,
			-1);
	static String userInput;
	static ArrayList<Employee> employees;
	static Employee employee;
	static Department department;

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		String userInputStr;
		greet();

		// while loop here eventually

		areWeReadingOrAdding();
		userInputStr = AorB(); // this returns a valid "a" or "b"
		if (userInputStr.contentEquals("a")) {
			addRoute();
		} else {
			findRoute();
		}

		// end of while loop here
		parting();

	}

	public static void areWeReadingOrAdding() {
		// prompt user
		System.out.println("\nDid you want to?");
		System.out.println("A - Add to the data base?");
		System.out.println("B - find an existing empoloyee or department head?");

	}

	public static void addRoute() {

		// prompt user
		System.out.println("\nAre you going to add : ");
		System.out.println("A - a new Employee?");
		System.out.println("B - a new Department?");

		String userInput = AorB();

		if (userInput.equals("a")) {
			createEmployee();
		} else {
			createDepartment();
		}

	}

	public static void createEmployee() {

		String userInput;
		String name;
		String email;
		String phoneNumber;
		String hierDate;
		boolean isDepartmentHead;
		Employee employee;
		Department department;

		System.out.println("\nCreating an a new hire!");
		System.out.println("What department are they going into?");
		userInput = scanny.nextLine();
		// scanny.nextLine();

		// find Department By type
		// department = new Department(userInput, 100, 2000d);
		// need to error if department not found
		System.out.println("\nWhat is their name?");
		name = scanny.nextLine();
		// scanny.nextLine();

		System.out.println("\nWhat is their phone number?");
		phoneNumber = scanny.nextLine();
		ReggieValidation rv = new ReggieValidation();
		if (rv.isValidPhoneNumber(phoneNumber)) {
			System.out.println("Valid phone number");
		} else {
			System.out.println("Invalid phone number");
		}

		// scanny.nextLine();

		// regex mabye
		System.out.println("\nWhat is their email?");
		email = scanny.nextLine();

		if (rv.isValidEmail(email)) {
			System.out.println("Valid email");
		} else {
			System.out.println("Invalid email");
		}
		// scanny.nextLine();

		// could use datetime here!
		// regex mabye
		System.out.println("\nWhat is todays date?");
		hierDate = scanny.nextLine();
		// scanny.nextLine();

		System.out.println("\nIs this new hier, " + name + ", going to be the head of the department? T/F?");
		userInput = TorF();
		// scanny.nextLine();

		// give user preview of what they are adding

		System.out.println("\nare you sure you want to add ");
		System.out.println("name : " + name);
		System.out.println("email : " + email);
		System.out.println("phone number : " + phoneNumber);
		// System.out.println("to the departmetn: " + department.getType());

		// possible secondary validation
		System.out.println("True or False ");
		userInput = TorF();

		if (userInput.equals("t")) {
			isDepartmentHead = true;
		} else {
			isDepartmentHead = false;
		}
		// to create a new employee instance
		// and connect them approiatly to the department information
		// employee = new Employee(name, email, phoneNumber, hierDate, department);

		// now add them back into the files, needs file logic form daniel
		// success message
		System.out.println("\nEmployee :");
		// System.out.println(employee);
		System.out.println("you have successfully added " + name + " to the employee list");
		System.out.println("go check them out on the files in resources//allEmployees.csv");
		return;
	}

	public static void createDepartment() {

		String userInput;
		String type;
		int phoneNumberExt;
		int headId;
		double budget;

		Employee head;

		System.out.println("\nCreating a new Department in the company?");
		System.out.println("What is the title type?");
		type = scanny.nextLine();

		System.out.println("What is the phone number extension?");
		phoneNumberExt = scanny.nextInt();

		System.out.println("What is the budget for " + type + "?");
		budget = scanny.nextDouble();

		System.out.println("Who is going to be the departmetn head for " + type + "?");
		System.out.println("Please input their employee id");

		headId = scanny.nextInt();
		// get employee by id
		// head = dummyEmployee;
		System.out.println("Is this the employee you want to appoint to the head of " + type + "?");

	}

	public static void findRoute() {

		System.out.println("\nAre you looking for a department details or employee details?");
		System.out.println("A - Department details?");
		System.out.println("B - Employee details?");

		String userInput = AorB();

		if (userInput.equals("a")) {
			findDepartmentDetails();
		} else {
			findEmployeeDetails();
		}

	}

	public static void findDepartmentDetails() {

		String title;
		Department searchedDepartment;

		System.out.println("Which department?");
		// Make a call to the file class
		String[] departmentTitles = { "HR", "Products", "Finance" };
		for (int i = 0; i < departmentTitles.length; i++) {
			System.out.print(departmentTitles[i]);
			if (i < departmentTitles.length - 1) {
				System.out.print("|");
			}
		}
		System.out.println();

		title = scanny.nextLine();

		// Make a call to the file reader
		searchedDepartment = dummyDepartment;
		System.out.println("title: " + searchedDepartment.getName());
		System.out.println("phone number extension: " + searchedDepartment.getPhoneNumberExt());
		System.out.println("budget: " + searchedDepartment.getBudget());
		// System.out.println("department head: " +
		// searchedDepartment.getDepartmentHead().getName());
		System.out.println("Would you like to see the list of all the employees in the department?");
		System.out.println("Y/N");

		String userInput = YorN();

		if (userInput.equals("y")) {
			listAllEmployeesInDepartment(searchedDepartment);
		}

	}

	public static void listAllEmployeesInDepartment(Department department) {
		// Read through files, returns list of employees
		// List<Employee> employees =

		// This is dummy data
		ArrayList<Employee> employees = fakeFileCall(department);

		for (int i = 0; i < employees.size(); i++) {
			System.out.println("Name: " + employees.get(i).getName() + "\tPhone number: "
					+ employees.get(i).getPhoneNumber() + "\tID: " + employees.get(i).getEmployeeId());

		}
	}

	public static void findEmployeeDetails() {
		System.out.println("Who would you like to know more about? Do you know their ID?");
		String userInput = YorN();

		if (userInput.equals("y")) {
			findUserByID();
		} else {
			System.out.println("What field are we searching by?");
			String field = scanny.nextLine();
			if (field.equals("name")) {
				System.out.println("What is their name?");
				String name = scanny.nextLine();
				// call to filereader that returns an array of employees w/ the matching name
				fakeFileCall();
				System.out.println("name : " + dummyEmployee.getName());
				System.out.println("email : " + dummyEmployee.getEmail());
				System.out.println("phone number : " + dummyEmployee.getPhoneNumber());
			}
		}
	}

	public static void findUserByID() {
		System.out.println("What is the employee's ID?");
		int userInput = scanny.nextInt();
		// call to the filereader class that returns a single employee via ID
		System.out.println("name : " + dummyEmployee.getName());
		System.out.println("email : " + dummyEmployee.getEmail());
		System.out.println("phone number : " + dummyEmployee.getPhoneNumber());
	}

	public static String booleanUserLogic(String x, String y) {

		String s = scanny.next();
		scanny.nextLine();
		if (s.equalsIgnoreCase(x.toLowerCase()) || s.equalsIgnoreCase(y.toLowerCase())) {
			return s.toLowerCase();
		} else {
			// invalid
			// more logic later
			return "";
		}
	}

	public static String AorB() {
		return booleanUserLogic("A", "B");
	}

	public static String TorF() {
		return booleanUserLogic("T", "F");
	}

	public static String YorN() {
		return booleanUserLogic("Y", "N");
	}

	public static void greet() {
		System.out.println("******************");
		System.out.println("Hello, Welcome to the Best Boughts Data Base");
	}

	public static void parting() {
		System.out.println("******************");
		System.out.println("Thanks for visiting the database of Best Boughts");
	}

	public static ArrayList<Employee> fakeFileCall(Department department) {
		return fakeFileCall();
	}

	public static ArrayList<Employee> fakeFileCall() {
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Employee(1, "Joe", "joe@google.com", "123-555-0000", "1990-08-27", 55000.00d, false, 1));
		employees.add(new Employee(2, "Bob", "bob@google.com", "123-555-0001", "1991-01-05", 15000.00d, false, 1));
		employees.add(new Employee(3, "Bill", "bill@google.com", "123-555-0002", "1994-04-16", 25000.00d, false, 1));
		employees.add(new Employee(4, "Ralph", "ralph@google.com", "123-555-0003", "1995-05-08", 10000.00d, false, 1));
		employees.add(new Employee(5, "James", "james@google.com", "123-555-0004", "1998-05-12", 20000.00d, false, 1));
		employees.add(
				new Employee(6, "Jessica", "jessica@google.com", "123-555-0005", "1999-01-09", 100000.00d, true, 1));
		employees.add(new Employee(7, "Peter", "peter@google.com", "123-555-0006", "2003-12-09", 90000.00d, false, 1));
		employees.add(new Employee(8, "Penny", "penny@google.com", "123-555-0007", "2005-08-11", 65000.00d, false, 2));
		employees.add(new Employee(9, "Jenny", "jenny@google.com", "123-555-0008", "2007-14-22", 34000.00d, false, 2));
		employees.add(new Employee(10, "Ellie", "ellie@google.com", "123-555-0009", "2008-09-21", 76000.00d, true, 2));
		employees.add(new Employee(11, "Jared", "jared@google.com", "123-555-0010", "2010-02-01", 12000.00d, true, 3));
		employees.add(new Employee(12, "Jordan", "jordan@google.com", "123-555-0011", "2019-08-16", 0.50d, true, 4));
		return employees;
	}

	public static ArrayList<Employee> fakeFileCall(String name) {
		return fakeFileCall();
	}
}
