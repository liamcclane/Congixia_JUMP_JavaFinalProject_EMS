package com.congnixia.javafinalproject.ems;

import java.util.ArrayList;
import java.util.Scanner;

import com.congnixia.javafinalproject.ems.models.*;

public class Demo {

	// lia's branch
	static Scanner scanny = new Scanner(System.in);

	static Department dummyDepartment = new Department("ExDep", 111, 3000.00d);
	static Employee dummyEmployee = new Employee("ExEmp", "dummy@dummy.com", "234-234-2344", "4/3/2020", dummyDepartment);
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
		department = new Department(userInput, 100, 2000d);
		// need to error if department not found
		System.out.println("\nWhat is their name?");
		name = scanny.nextLine();
		// scanny.nextLine();

		System.out.println("\nWhat is their phone number?");
		phoneNumber = scanny.nextLine();
		// scanny.nextLine();

		// regex mabye
		System.out.println("\nWhat is their email?");
		email = scanny.nextLine();
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
		System.out.println("to the departmetn: " + department.getType());

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
		employee = new Employee(name, email, phoneNumber, hierDate, department);

		// now add them back into the files,
		// success message
		System.out.println("\nEmployee :");
		System.out.println(employee);
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
		
		System.out.println("Who is going to be the departmetn head for " + type +"?");
		System.out.println("Please input their employee id");
		
		headId = scanny.nextInt();
		// get employee by id
		head = dummyEmployee;
		System.out.println("Is this the employee you want to appoint to the head of " + type + "?");
		
		
		
		
	}

	public static void findRoute() {

		System.out.println();
		
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
}
