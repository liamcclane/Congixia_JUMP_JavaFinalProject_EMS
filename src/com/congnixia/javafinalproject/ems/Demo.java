package com.congnixia.javafinalproject.ems;

import java.util.Scanner;

import com.congnixia.javafinalproject.ems.models.*;

public class Demo {

	// lia's branch
	static Scanner scanny = new Scanner(System.in);

	static Department dummyDepartment = new Department("ExDep", 111, 3000.00d);
	static Employee dummyEmployee = new Employee("ExEmp", "dummy@dummy.com", "234-234-2344", "4/3/2020", dummyDepartment);
	
	public static void main(String[] args) {

		start();
		/**
		 * 
		 * goal is to demo our EMS for Best Boughts Store
		 * 
		 * when we run this file,
		 * 
		 * greet user, ask what are we doing if we are adding DB (Either Employee or
		 * Department) looking for some information that is already stored
		 * 
		 * partting
		 * 
		 */

		/**
		 * 
		 * if we are adding prompt user, what are we adding? Employee, or opening a new
		 * department
		 * 
		 * If employee, prompt for information name Department they are going into
		 * output the newly made employee back to the user
		 * 
		 * then loop back to the top
		 * 
		 * 
		 * 
		 */

		/**
		 * 
		 * if we are looking some one up
		 * 
		 * output to user the filds they can search by
		 * 
		 * id, name, department,
		 * 
		 * user will type in "name" or "id" if name "who are we looking for" if id "2"
		 * // if extra time maybe take "10 (though) 20" as valid
		 * 
		 * output either the single use they are looking for or the list of people
		 * 
		 * if it was a list a people, ask for a number from the user for which person
		 * was the correct one. output that Employee to the user
		 * 
		 * if only one person matches what they are looking for output the Employee to
		 * the user
		 * 
		 * 
		 * Prompt yes or no if they would like to update them
		 * 
		 */

		/**
		 * 
		 * if the user wants to update the Employee ask them which field they want to
		 * change name, department "you are changing {{Employee}}'s {{field}}
		 * information" "what would you like to change it to?"
		 * 
		 * "confirmation message" {{updated employees info}}
		 * 
		 */

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
