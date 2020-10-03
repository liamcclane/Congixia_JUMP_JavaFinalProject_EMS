package com.congnixia.javafinalproject.ems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.congnixia.javafinalproject.ems.exceptions.*;
import com.congnixia.javafinalproject.ems.models.*;
import com.sun.org.apache.xpath.internal.FoundIndex;

public class Demo {

	// lia's branch
	static Scanner scanny = new Scanner(System.in);
	static Department dummyDepartment = new Department(0, "ExDep", 0, "111", 3000.00d);
	static Employee dummyEmployee = new Employee(-1, "fake", "fake@fake.com", "000-000-000", "1/1/11", 1000d, false,
			-1);
	static String userInput;
	static Employee employee;
	static Department department;
	static String[] allDepartmentNames = Department.getAllDepartmentNames();
	static List<Employee> employees = Employee.listEmployees();

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		String again;
		greet();
		do {
			intoTheDataBase();
			System.out.println("Do you want continue making changes? Y/N");

			again = scanny.nextLine();
		} while (again.equalsIgnoreCase("yes") || again.equals("y"));
		parting();
	}

	public static void intoTheDataBase() {
		System.out.println("\nDid you want to?");
		System.out.println("A - Add to the data base?");
		System.out.println("B - find an existing empoloyee details or department details?");
		String aOrB = AorBLoop();
		if (aOrB.equals("a")) {
			addRoute();
		} else {
			findRoute();
		}
	}

	public static void addRoute() {
		// prompt user
		System.out.println("\nAre you going to add : ");
		System.out.println("A - a new Employee?");
		System.out.println("B - a new Department?");
		String aOrB = AorBLoop();
		if (aOrB.equals("a")) {
			createEmployee();
		} else {
			createDepartment();
		}
	}

	public static void createEmployee() {
		String name;
		String email;
		String phoneNumber;
		String hierDate;
		double salary = 0.0d;
		boolean isDepartmentHead;
		System.out.println("\nCreating an a new hire!");
		System.out.println("What department are they going into?");
		// list departments
		printDepartmentNames();
		String departName = "";
		do {
			try {
				departName = getUserTitle();
				break;
			} catch (NotValidDepartmentOption e) {
				System.out.println(e.getMessage());
			}

		} while (true);
		Department foundDepartment = Department.findDepartmentByName(departName);
		// want to find current head
		System.out.println("\nWhat is their name?");
		name = scanny.nextLine(); // null exeption maybe

		ReggieValidation rv = new ReggieValidation();
		System.out.println("\nWhat is their phone number?");
		phoneNumber = scanny.nextLine();
		while (!rv.isValidPhoneNumber(phoneNumber)) {
			System.err.println("Invalid phone number must be (###)###-####");
			System.err.println("or ###-###-####");
			scanny.nextLine();
			phoneNumber = scanny.nextLine();
		}

		do {
			System.out.println("\nWhat is their email?");
			email = scanny.nextLine();
			if (!rv.isValidEmail(email)) {
				System.err.println("Invalid email");
				System.err.println("ex example@eample.com");
				scanny.nextLine();
			} else {
				break;
			}
		} while (true);

		// could use datetime here!
		// regex mabye
		System.out.println("\nWhat is," + name + ", salary?");

		do {
			try {
				salary = scanny.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
				scanny.nextLine();
			}

		} while (true);

		System.out.println(
				"\nThe current head of " + foundDepartment.getName() + ", is " + foundDepartment.getEmployeeId() + ".");
		System.out.println("would you like to appoint the new heir, " + name + ", to be the head?" + "\nY/N?");
		String whatever = YorNLoop();
		if (whatever.equals("y")) {
			isDepartmentHead = true;
		} else {
			isDepartmentHead = false;
		}

		// give user preview of what they are adding
		System.out.println("\nare you sure you want to add ");
		System.out.println("name : " + name);
		System.out.println("email : " + email);
		System.out.println("salary : " + salary);
		System.out.println("phone number : " + phoneNumber);
		if (whatever.equals("y")) {
			System.out.println("and appoint the new comer department head over, " + foundDepartment.getEmployeeId());
		} else {
			System.out.println(
					"to the department: " + foundDepartment.getName() + " under " + foundDepartment.getEmployeeId());
		}

		// possible secondary validation
		
		System.out.println("T/F?");
		whatever = TorFLoop();

		if (whatever.equals("t")) {
			// add to the database
			System.out.println("add to the database");
			// to create a new employee instance
			// and connect them approiatly to the department information
			Employee newHeir = new Employee(Employee.getLastEmployeeId(), name, email, phoneNumber, "1/1/1", salary,
					isDepartmentHead, foundDepartment.getDepartmentId());

			// now add them back into the files, needs file logic form daniel
			// success message
			// System.out.println(employee);
			System.out.println("\nEmployee :");
			System.out.println("you have successfully added " + name + " to the employee list");
			System.out.println(dummyEmployee.toString());
			System.out.println("go check them out on the files in resources//allEmployees.csv");
		} else {
			System.out.println("CANCELING creating an empoyee");
		}
	}

	public static void createDepartment() {

		String userInput;
		String type;
		String phoneNumberExt;
		int headId;
		double budget;

		Employee head;

		System.out.println("\nCreating a new Department in the company?");
		System.out.println("What is the title type?");
		
		do {
			try {
				type = getUserDepatType();
				break;
			} catch (DepartmentAlreadyExistsException e) {
				System.err.println(e.getMessage());
				printDepartmentNames();
			}
		} while (true);


		System.out.println("What is the phone number extension?");
		do {
			try {
				phoneNumberExt = getValidExtInt();
				break;
			} catch (NotValidExtensionNumber e) {
				System.err.println(e.getMessage());
			}
		} while (true);

		System.out.println("What is the budget for " + type + "?");

		do {
			try {
				budget = scanny.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
			}
		} while (true);

		System.out.println("Who is going to be the department head for " + type + "?");
		System.out.println("Please input their employee id");

		do {
			try {
				headId = scanny.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
			}
		} while (true);
		// get employee by id
		// check if user has input deleted id, or an id outside of your list of
		// employees
		// head = dummyEmployee;
		System.out.println("Is this the employee you want to appoint to the head of " + type + "?");
		Employee empToAppoint;
		try {
			empToAppoint = Employee.findEmployeeById(headId);
			empToAppoint.prettyPrintln();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Y/N?");
		String whatever;
		do {
			try {
				whatever = YorN();
				break;
			} catch (NotYesOrNoException e) {
				System.err.println(e.getMessage());
			}
		} while (true);

		if (whatever.equals("y")) {
			// other function
			// logic
			Department newestDepart = null;
			newestDepart = new Department(Department.getLastDepartmentId(), type, dummyEmployee.getEmployeeId(),
					phoneNumberExt, 10000);
			// success message
			System.out.println("you have created a new department," + newestDepart.getName() + "! with "
					+ dummyEmployee.getName() + " as the head!");
			return;
		} else {
			System.out.println("CANCELING creating a department");
		}
	}

	public static void findRoute() {

		System.out.println("\nAre you looking for a department details or employee details?");
		System.out.println("A - Department details?");
		System.out.println("B - Employee details?");
		String aOrB = AorBLoop();
		if (aOrB.equals("a")) {
			findDepartmentDetails();
		} else {
			findEmployeeDetails();
		}

	}

	public static void findDepartmentDetails() {
		String title;
		Department searchedDepartment;
		System.out.println("Which department?");
		printDepartmentNames();
		do {
			try {
				title = getUserTitle();
				break;
			} catch (NotValidDepartmentOption e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		// Make a call to the file reader
		searchedDepartment = Department.findDepartmentByName(title);
		System.out.println("title: " + searchedDepartment.getName());
		System.out.println("phone number extension: " + searchedDepartment.getPhoneNumberExt());
		System.out.println("budget: " + searchedDepartment.getBudget());
		// System.out.println("department head: " +
		// searchedDepartment.getDepartmentHead().getName());
		System.out.println("Would you like to see the list of all the employees in the department?");
		System.out.println("Y/N");
		String aOrB = YorNLoop();
		if (aOrB.equals("y")) {
			List<Employee> employees;
			try {
				employees = Department.findAllEmployeesWorkingInDepartment(searchedDepartment);
				for (int i = 0; i < employees.size(); i++) {
					System.out.println("Name: " + employees.get(i).getName() + "\tPhone number: "
							+ employees.get(i).getPhoneNumber() + "\tID: " + employees.get(i).getEmployeeId());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Would you like to make edits to this Department? \nY/N");
		aOrB = YorNLoop();
		if (aOrB.equals("y")) {
			editDepartmentRoute(searchedDepartment);
		} else {
			System.out.println("do you want to delete this department " + searchedDepartment.getName());
			aOrB = YorNLoop();
			if(aOrB.equals("y")) {
				System.out.println("deleting department successfully " ); // DELETE
			} else {
				System.out.println("end line 336");
			}
		}
		

	}

	public static void editDepartmentRoute(Department oldDepartment) {
		System.out.println(oldDepartment);
		System.out.println("what should the new name be for" + oldDepartment.getName() +"?" );
		String name = scanny.nextLine();
		System.out.println("what should the new ext. Number be? currently" + oldDepartment.getPhoneNumberExt());
		String extNum = scanny.nextLine();
		System.out.println("what should the new budget be?");
		double budget = scanny.nextDouble();
		System.out.println("keep the same department head? Y/N");
		Employee currHead = dummyEmployee;
		try {
			currHead = Employee.findEmployeeById(oldDepartment.getEmployeeId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
		String yOrN = YorNLoop();
		
		
		
		
	}
	
	
	public static void listAllEmployeesInDepartment(Department department) {
		// Read through files, returns list of employees
		// List<Employee> employees =

		// This is dummy data
	}

	public static void findEmployeeDetails() {
		System.out.println("Who would you like to know more about? Do you know their ID?");
		String whatever;
		do {
			try {
				whatever = YorN();
				break;
			} catch (NotYesOrNoException e) {
				System.err.println(e.getMessage());
			}
		} while (true);

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
		dummyEmployee.prettyPrintln();
	}

	public static String getAorBAgain() throws NotAorBException {
		String aOrB;
		aOrB = scanny.nextLine();

		if (!(aOrB.toLowerCase().equals("a") || aOrB.toLowerCase().equals("b"))) {
			throw new NotAorBException(aOrB);
		}

		return aOrB.toLowerCase();
	}

	public static String YorNLoop() {
		String whatever;
		do {
			try {
				whatever = YorN();
				break;
			} catch (NotYesOrNoException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		return whatever.toLowerCase();
	}
	
	public static String TorFLoop() {
		String whatever;
		do {
			try {
				whatever = TorF();
				break;
			} catch (NotTorFException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		return whatever.toLowerCase();
	}
	
	public static String AorBLoop() {
		String result = "";
		do {
			try {
				result = getAorBAgain();
				break;
			} catch (NotAorBException e) {
				System.err.println(e.getMessage());
			}
		} while (true);
		return result.toLowerCase();
	}
	
	public static String TorF() throws NotTorFException {
		String aOrB;
		aOrB = scanny.nextLine();

		if (!(aOrB.toLowerCase().equals("t") || aOrB.toLowerCase().equals("f"))) {
			throw new NotTorFException(aOrB);
		}

		return aOrB.toLowerCase();
	}

	public static String YorN() throws NotYesOrNoException {
		String aOrB;
		aOrB = scanny.nextLine();

		if (!(aOrB.toLowerCase().equals("y") || aOrB.toLowerCase().equals("n"))) {
			throw new NotYesOrNoException(aOrB);
		}

		return aOrB.toLowerCase();
	}

	public static String getUserTitle() throws NotValidDepartmentOption {
		String name = scanny.nextLine();
		for (String dName : allDepartmentNames) {
			if (name.toLowerCase().equalsIgnoreCase(dName)) {
				return name;
			}
		}
		throw new NotValidDepartmentOption(name);
	}

	public static String getValidExtInt() throws NotValidExtensionNumber {
		String reggie = "[0-9]{3}";
		String s = scanny.nextLine();
		if (s.length() != 3) {
			throw new NotValidExtensionNumber(s);
		}
		Pattern patty = Pattern.compile(reggie);
		Matcher m = patty.matcher(s);
		if (!m.matches()) {
			throw new NotValidExtensionNumber(s);
		}
		return s;
	}

	/**
	 * prints all the department names on one line
	 */
	public static void printDepartmentNames() {
		for (String dName : allDepartmentNames) {
			System.out.print(dName + "|");
		}
		System.out.println();
	}
	public static String getUserDepatType() throws DepartmentAlreadyExistsException {
		String tryType = scanny.nextLine();
		for (String dName : allDepartmentNames) {
			if(tryType.toLowerCase().equalsIgnoreCase(dName)) {
				throw new DepartmentAlreadyExistsException(tryType);
			}
		}
		return tryType;
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

	public static Department fakeFileCallForDepartment(String name) {
		return dummyDepartment;
	}
}
