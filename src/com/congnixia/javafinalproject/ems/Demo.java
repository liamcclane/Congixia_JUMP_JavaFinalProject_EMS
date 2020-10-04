package com.congnixia.javafinalproject.ems;

import java.io.IOException;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.congnixia.javafinalproject.ems.exceptions.*;
import com.congnixia.javafinalproject.ems.models.*;
/**
 * 
 * @author lia_m, josh_t, daniel_i
 * delete route line 
 */
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
		} while (again.equalsIgnoreCase("yes") || again.equalsIgnoreCase("y"));
		parting();
	}

	public static void intoTheDataBase() {
		System.out.println("\nDid you want to?");
		System.out.println("A - Add to the data base?");
		System.out.println("B - Find an existing employee details or department details?");
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

		System.out.println("\nWhat is their phone number?");
		phoneNumber = getUserPhoneNumber();
		System.out.println("\nWhat is their email?");
		email = getUserEmail();
		System.out.println("\nWhat is the new employee," + name + " , salary going to be?");
		do {
			try {
				salary = scanny.nextDouble();
				if(salary <= 0) {
					throw new NotValidDepartmentOption("asd");
				}
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
				scanny.nextLine();
			} catch (NotValidDepartmentOption e) {
				System.err.println("cannot be neagitive");
			}
		} while (true);

		try {
			System.out.println(
					"\nThe current head of " + foundDepartment.getName() + ", is " + Employee.findEmployeeById(foundDepartment.getEmployeeId()).getName() + ".");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			try {
				System.out.println(

						"to the department: " + foundDepartment.getName() + " under " + Employee.findEmployeeById(foundDepartment.getEmployeeId()).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Y/N?");
		whatever = YorNLoop();
		if (whatever.equals("y")) {
			// add to the database
			// to create a new employee instance
			// and connect them approiatly to the department information
			Employee newHeir = new Employee(Employee.getLastEmployeeId(), name, email, phoneNumber, "1/1/1", salary,
					isDepartmentHead, foundDepartment.getDepartmentId());
			Employee.addEmployee(newHeir);
			// now add them back into the files, needs file logic form daniel
			// success message
			// System.out.println(employee);
			System.out.println("add to the database");
			System.out.println("\nEmployee :");
			System.out.println(newHeir.toString());
			System.out.println("you have successfully added " + name + " to the employee list");
			System.out.println("go check them out on the files in resources//allEmployees.csv");
		} else {
			System.out.println("CANCELING creating an empoyee");
		}
	}

	public static void createDepartment() {

		String type;
		String phoneNumberExt;
		int headId;
		double budget;
		Employee empToAppoint = dummyEmployee;

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

		do {
			try {
				System.out.println("Please input their employee id");
				headId = scanny.nextInt();
				if (headId > Employee.getLastEmployeeId()) {
					System.out.println("id " + headId + " not found");
					throw new NotValidDepartmentOption("blah");
				}
				empToAppoint = Employee.findEmployeeById(headId);
				if (empToAppoint == null) {
					System.out.println("id " + headId + " not found");
					throw new NotValidDepartmentOption("s");
				}
				empToAppoint.prettyPrintln();
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
			} catch (NotValidDepartmentOption e) {
				System.err.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} while (true);
		// get employee by id
		// check if user has input deleted id, or an id outside of your list of
		// employees
		// head = dummyEmployee;

		System.out.println("Is this the employee you want to appoint to the head of " + type + "?");
		System.out.println("Y/N?");
		String whatever;
		whatever = YorNLoop();

		if (whatever.equals("y")) {
			// other function
			// logic
			Department newestDepart = null;
			newestDepart = new Department(Department.getLastDepartmentId(), type, empToAppoint.getEmployeeId(),
					phoneNumberExt, budget);
			// success message
			boolean b = Department.addDepartment(newestDepart);
			if(b) {
				System.out.println("you have created a new department," + newestDepart.getName() + "! with "
						+ empToAppoint.getName() + " as the head!");
			}
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
				printDepartmentNames();
			}
		} while (true);
		// Make a call to the file reader
		searchedDepartment = Department.findDepartmentByName(title);
		Employee currHead = dummyEmployee;
		try {
			currHead = Employee.findEmployeeById(searchedDepartment.getEmployeeId());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("title: " + searchedDepartment.getName());
		System.out.println("phone number extension: " + searchedDepartment.getPhoneNumberExt());
		System.out.println("budget: " + searchedDepartment.getBudget());
		System.out.println("department head: " + currHead.getName());
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
			editDepartmentRoute(searchedDepartment, currHead);
		} else {
			System.out.println("do you want to delete this department " + searchedDepartment.getName());
			aOrB = YorNLoop();
			if (aOrB.equals("y")) {
				System.out.println("deleting department successfully "); // DELETE Department.delete();
			}
		}

	}

	public static void editDepartmentRoute(Department oldDepartment, Employee currHead) {
		System.out.println(oldDepartment);
		System.out.println("what should the new name be for" + oldDepartment.getName() + "?");
		String type;
		do {
			try {
				type = getUserDepatType();
				break;
			} catch (DepartmentAlreadyExistsException e) {
				System.err.println(e.getMessage());
				printDepartmentNames();
			}
		} while (true);

		System.out.println("what should the new ext. Number be? currently" + oldDepartment.getPhoneNumberExt());
		String extNum;
		do {
			try {
				extNum = getValidExtInt();
				break;
			} catch (NotValidExtensionNumber e) {
				System.err.println(e.getMessage());
			}
		} while (true);

		System.out.println("what should the new budget be?");
		double budget;
		do {
			try {
				budget = scanny.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
			}
		} while (true);

		System.out.println("keep the same department head, " + currHead.getName() + "? Y/N");
		String yOrN = YorNLoop();
		Employee empToAppoint = currHead;
		int headId = currHead.getEmployeeId();
		if (yOrN.equals("y")) {
			do {
				try {
					System.out.println("Please input their employee id");
					headId = scanny.nextInt();
					if (headId > Employee.getLastEmployeeId()) {
						System.out.println("id " + headId + " not found");
						throw new NotValidDepartmentOption("blah");
					}
					empToAppoint = Employee.findEmployeeById(headId);
					if (empToAppoint == null) {
						System.out.println("id " + headId + " not found");
						throw new NotValidDepartmentOption("s");
					}
					empToAppoint.prettyPrintln();
					oldDepartment.setEmployeeId(headId);
					Department.makeEmployeeHeadOfDepartment(currHead, oldDepartment);
					break;
				} catch (InputMismatchException e) {
					System.err.println("not an number");
				} catch (NotValidDepartmentOption e) {
					System.err.println(e.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} while (true);
		}

		System.out.println("updating old department to the new information");
		oldDepartment.setName(type);
		oldDepartment.setBudget(budget);
		oldDepartment.setPhoneNumberExt(extNum);
		boolean b = Department.updateDepartment(oldDepartment.getDepartmentId(), oldDepartment);
		if (b) {
			System.out.println("Successfully changed department, " + type + ", to new information ");
		}
	}

	public static void findEmployeeDetails() {
		String whatever;
		Employee employee = dummyEmployee;
		List<Employee> potentialEmployees;
		System.out.println("Who would you like to know more about? Do you know their ID?\nY/N");
		whatever = YorNLoop();
		if (whatever.equals("y")) {
			employee = findUserByID();
		} else {
			potentialEmployees = findEmpByName();
			if(potentialEmployees.size() == 1) {
				employee = potentialEmployees.get(0);
			} else  {
				for (Employee employee2 : potentialEmployees) {
					employee2.prettyPrint();
				}
			}
			
		}
		employee.prettyPrintln();
		System.out.println("Would you like to edit " + employee.getName() + "\nY/N?");
		whatever = YorNLoop();
		if(whatever.equals("y")) {
			editEmployee(employee);
			return;
		}
		System.out.println("Would you like to delete" + employee.getName() + "\nY/N?");
		whatever = YorNLoop();
		if(whatever.equals("y")) {
			boolean  b = Employee.removeEmployee(employee.getEmployeeId());
			if(b) {
				System.out.println("\nEmployee was deleted!\n");
			}
		}
	}

	public static void editEmployee(Employee employee) {
		Department department = dummyDepartment;
		System.out.println("What do you want change this employee's phone number?");
		System.out.println(employee.getName() + " current Number " + employee.getPhoneNumber());
		employee.setPhoneNumber(getUserPhoneNumber());
		System.out.println("What do you want change this employee's email?");
		System.out.println(employee.getName() + " current email " + employee.getEmail());
		employee.setPhoneNumber(getUserEmail());
		System.out.println("What do you want change this employee's salary?");
		System.out.println(employee.getName() + " current salary " + employee.getSalary());
		double salary = 0.0d;
		do {
			try {
				salary = scanny.nextDouble();
				scanny.nextLine();
				break;
			} catch (Exception e) {
				System.err.println("not a number");
				scanny.nextLine();
			}
		} while (true);
		employee.setSalary(salary);
		System.out.println("Is this employee changing Departments?\nY/N");
		String whatever = YorNLoop();
		if (whatever.equals("y")) {
			department = Department.findDepartmentById(employee.getDepartmentId());
			if (employee.isDepartmentHead()) {
				System.out.println("It looks like this employee" + employee.getName()
						+ " is already the head of thier department in " + department.getName());
			} else {
				System.out.println("Okay we are appointing " + employee.getName() + " to the head of " + department.getName());
				System.out.println("And " + department.getName() + " has been fired!");
				try {
					Employee oldHead;
					oldHead = Employee.findEmployeeById(department.getEmployeeId());
					System.out.println("over throwing " + oldHead.getName());
					boolean b = Department.makeEmployeeHeadOfDepartment(employee, department);
					if(!b) {
						System.out.println("couldn't appoint " + employee.getName() + "as the head of " + department.getName());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		boolean b = Employee.updateEmployee(employee.getEmployeeId(), employee);
		if (b) {
			System.out.println("update complete check employee.csv");
		}
	}

	public static List<Employee> findEmpByName() {
		do {
			try {
				System.out.println("What is their name?");
				String name = scanny.nextLine();
				if (Employee.findAllEmployeesByName(name) == null) {
					System.out.println(name + " does not match any employee");
					throw new NotValidDepartmentOption("blah");
				}
				return Employee.findAllEmployeesByName(name);
			} catch (NotValidDepartmentOption e) {
				e.printStackTrace();
			}
		} while (true);
	}

	public static Employee findUserByID() {
		int headId = dummyEmployee.getEmployeeId();
		Employee empToAppoint = dummyEmployee;
		do {
			try {
				System.out.println("Please input their employee id");
				headId = scanny.nextInt();
				if (headId > Employee.getLastEmployeeId()) {
					System.out.println("id " + headId + " not found");
					throw new NotValidDepartmentOption("blah");
				}
				empToAppoint = Employee.findEmployeeById(headId);
				if (empToAppoint == null) {
					System.out.println("id " + headId + " not found");
					throw new NotValidDepartmentOption("s");
				}
				empToAppoint.prettyPrintln();
				return empToAppoint;
			} catch (InputMismatchException e) {
				System.err.println("not an number");
			} catch (NotValidDepartmentOption e) {
				System.err.println(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} while (true);
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

	public static String getUserPhoneNumber() {
		String phNum;
		do {
			try {
				phNum = userPhoneNumberEx();
				break;
			} catch (NotValidDepartmentOption e) {
				System.out.println(e.getMessage());
			}
		} while (true);
		return phNum;
	}

	public static String getUserEmail() {
		String phNum;
		do {
			try {
				phNum = userEmailEx();
				break;
			} catch (NotValidDepartmentOption e) {
				System.out.println(e.getMessage());
			}
		} while (true);
		return phNum;
	}

	public static String userPhoneNumberEx() throws NotValidDepartmentOption {
		String s = scanny.nextLine();
		ReggieValidation rv = new ReggieValidation();
		if (rv.isValidPhoneNumber(s)) {
			return s;
		} else {
			System.err.println("Invalid phone number must be (###)###-####");
			System.err.println("or ###-###-####");
			throw new NotValidDepartmentOption(s);
		}
	}

	public static String userEmailEx() throws NotValidDepartmentOption {
		String s = scanny.nextLine();
		ReggieValidation rv = new ReggieValidation();
		if (rv.isValidEmail(s)) {
			return s;
		} else {
			System.err.println("example@eample.com");
			throw new NotValidDepartmentOption(s);
		}
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
			if (tryType.toLowerCase().equalsIgnoreCase(dName)) {
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
}
