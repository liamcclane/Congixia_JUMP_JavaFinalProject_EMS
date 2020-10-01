package com.congnixia.javafinalproject.ems;

import java.util.Scanner;

import com.congnixia.javafinalproject.ems.models.Department;

public class Demo {

	// lia's branch
	static Scanner scanny = new Scanner(System.in);

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

	public static void greet() {
		System.out.println("******************");
		System.out.println("Hello, Welcome to the Best Boughts Data Base");
	}

	public static void parting() {
		System.out.println("******************");
		System.out.println("Thanks for visiting the database of Best Boughts");
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
		System.out.println("Did you want to?");
		System.out.println("A - Add to the data base?");
		System.out.println("B - find an existing empoloyee or department head?");

	}

	public static void addRoute() {

		// prompt user
		System.out.println("Are you trying to find?");
		System.out.println("A - an Employee?");
		System.out.println("B - Department details?");

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
		Department department;
		
		System.out.println();
		System.out.println("Creating an a new hire!");
		System.out.println("What department are they going into?");
		userInput = scanny.nextLine();
		scanny.nextLine();
		System.out.println();
		
	}

	public static void createDepartment() {

	}

	public static void findRoute() {

	}

	public static String AorB() {
		String s = scanny.next();
		scanny.nextLine();
		if (s.equalsIgnoreCase("a") || s.equalsIgnoreCase("b")) {
			return s.toLowerCase();
		} else {
			// invalid
			// more logic later
			return "";
		}
	}
}
