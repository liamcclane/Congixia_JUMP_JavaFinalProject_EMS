package com.congnixia.javafinalproject.ems;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.congnixia.javafinalproject.ems.exceptions.NotValidExtensionNumber;
import com.congnixia.javafinalproject.ems.models.Department;
import com.congnixia.javafinalproject.ems.models.Employee;

public class BestBoughts {

	private static Scanner scan = new Scanner(System.in);
	private static ReggieValidation rv = new ReggieValidation();
	public static void main(String[] args) {

		mainMenu();
	}
	
	private static void mainMenu() {
		System.out.println("Pick an option (A, B, or C):");
		System.out.println("------------------------");
		System.out.println("A - View Employees");
		System.out.println("B - View Departments");
		System.out.println("C - Exit Program");
		
		String newStr = scan.nextLine();
		
		while(!(newStr.equalsIgnoreCase("A") || newStr.equalsIgnoreCase("B") || newStr.equalsIgnoreCase("C"))) {
			System.out.println("You have entered in: " + newStr + "\nPlease enter in either A or B:");
			newStr = scan.nextLine();
		}
		
		if(newStr.equalsIgnoreCase("A")) {
			viewEmployees();
		} else if(newStr.equalsIgnoreCase("B")) {
			viewDepartments();
		} else if(newStr.equalsIgnoreCase("C")) {
			System.out.println("THANK YOU FOR USING THIS APPLICATION!!!");
			System.exit(0);
		}
	}
	
	private static void printEmployeeBanner() {
		System.out.println(" ===============================================================================================================================================");
		System.out.println("| id	| firstName	| lastName	| Email				| phonenumber	| salary	| isDepartmentHead	| departmentid	|");
		System.out.println(" ===============================================================================================================================================");
	}
	
	private static void printEmployeeBannerLine() {
		System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	private static void printDepartmentBanner() {
		System.out.println(" =======================================================================================");
		System.out.println("| departmentId	| name		| employeeId	| phoneNumberExt	| budget	|");
		System.out.println(" =======================================================================================");
	}
	
	private static void printDepartmentBannerLine() {
		System.out.println(" ---------------------------------------------------------------------------------------");
	}

	private static void viewEmployees() {
		System.out.println("\nEmployees:");
		printEmployeeBanner();
		List<Employee> emps = Employee.listEmployees();
		for(Employee emp: emps) {
			System.out.println(printEmployee(emp));
			printEmployeeBannerLine();
		}
		System.out.println();
		employeeView();
	}
	
	private static void viewDepartments() {
		System.out.println("\nDepartments:");
		printDepartmentBanner();
		List<Department> dept = Department.listDepartments();
		for(Department dep: dept) {
			System.out.println(printDepartment(dep));
			printDepartmentBannerLine();
		}
		System.out.println();
		departmentView();
	}
	
	private static void employeeView() {
		System.out.println("Pick an option (A, B, C, or D):");
		System.out.println("-------------------------------");
		System.out.println("A - View/Edit an employee.");
		System.out.println("B - Add an employee.");
		System.out.println("C - Remove an employee.");
		System.out.println("D - Return to main menu.");
		
		String newStr = scan.nextLine();
		
		while(!(newStr.equalsIgnoreCase("A") || newStr.equalsIgnoreCase("B") || newStr.equalsIgnoreCase("C") || newStr.equalsIgnoreCase("D"))) {
			System.out.println("You have entered in: " + newStr + "\nPlease enter in either A, B, C, or D:");
			newStr = scan.nextLine();
		}
		
		if(newStr.equalsIgnoreCase("A")) {
			if(Employee.listEmployees().size() == 0) {
				System.out.println("INFO: There are no employees to view.");
				viewEmployees();
			}
			System.out.println("Enter in the id of the person to view or edit");
			selectedViewEmployee(validInterger());
		} else if(newStr.equalsIgnoreCase("B")) {
			if(Department.listDepartments().size() == 0) {
				System.out.println("WARNING: Cannot add an employee without a department first.");
				viewEmployees();
			}
			addEmployee();
		} else if(newStr.equalsIgnoreCase("C")) {
			removeEmployee();
		} else if(newStr.equalsIgnoreCase("D")) {
			mainMenu();
		}
	}
	
	private static void departmentView() {
		System.out.println("Pick an option (A, B, C, or D):");
		System.out.println("-------------------------------");
		System.out.println("A - View/Edit a Department.");
		System.out.println("B - Add a Department.");
		System.out.println("C - Remove a Department.");
		System.out.println("D - Return to Main Menu.");
		
		String newStr = scan.nextLine();
		
		while(!(newStr.equalsIgnoreCase("A") || newStr.equalsIgnoreCase("B") || newStr.equalsIgnoreCase("C") || newStr.equalsIgnoreCase("D"))) {
			System.out.println("You have entered in: " + newStr + "\nPlease enter in either A, B, C, or D:");
			newStr = scan.nextLine();
		}
		
		if(newStr.equalsIgnoreCase("A")) {
			if(Department.listDepartments().size() == 0) {
				System.out.println("INFO: There are no departments to view.");
				viewDepartments();
			}
			System.out.println("Enter in the id of the department to view or edit");
			selectedViewDepartment(validInterger());
		} else if(newStr.equalsIgnoreCase("B")) {
			addDepartment();
		} else if(newStr.equalsIgnoreCase("C")) {
			removeDepartment();
		} else if(newStr.equalsIgnoreCase("D")) {
			mainMenu();
		}
	}
	
	private static String printEmployee(Employee emp) {
		String newString = "";
		newString += "| " + emp.getEmployeeId() + "	| " + emp.getFirstName();
		if(emp.getFirstName().length() < 6) {
			newString += "		| ";
		} else {
			newString += "	| ";
		}
		newString += emp.getLastName();
		if(emp.getLastName().length() < 6) {
			newString += "		| ";
		} else {
			newString += "	| ";
		}
		newString += emp.getEmail();
		if(emp.getEmail().length() < 14) {
			newString += "			| ";
		} else if(emp.getEmail().length() < 22) {
			newString += "		| ";
		}
		else {
			newString += "	| ";
		}
		newString += emp.getPhoneNumber() + "	| " + emp.getSalary();
		if(emp.getSalary() + "".length() < 6 || emp.getSalary() > 9999999) {
			newString += "		| ";
		} else {
			newString += "	| ";
		}
		newString += emp.isDepartmentHead();
		if(emp.isDepartmentHead()) {
			newString += "	 		| ";
		} else {
			newString += " 		| ";
		}
		newString += emp.getDepartmentId() + "		|";
		return newString;
	}
	
	
	private static String printDepartment(Department dept) {
		String newString = "| " + dept.getDepartmentId() + "		| " + dept.getName();
		if(dept.getName().length() < 6) {
			newString += "		| ";
		} else {
			newString += "	| ";
		}
		newString += dept.getEmployeeId() + "		| " + dept.getPhoneNumberExt() + "			| " + dept.getBudget();
		if(dept.getBudget() + "".length() < 6) {
			newString += "		|";
		} else {
			newString += "	|";
		}
		return newString;
	}
	
	
	
	
	
	private static void selectedViewEmployee(int index) {
		while(Employee.findEmployeeById(index) == null) {
			System.out.println("Enter a valid id in the table: ");
			index = validInterger();
		}
		printEmployeeBanner();
		System.out.println(printEmployee(Employee.findEmployeeById(index)));
		printEmployeeBannerLine();
		System.out.println();
		System.out.println("Pick an option (A, B, C, D, E, F, G, H, or I):");
		System.out.println("-------------------------------------------");
		System.out.println("A - Edit First Name.");
		System.out.println("B - Edit Last Name.");
		System.out.println("C - Edit Email.");
		System.out.println("D - Edit Phone Number.");
		System.out.println("E - Edit Salary.");
		System.out.println("F - Edit Deapartment Head.");
		System.out.println("G - Edit Department.");
		System.out.println("H - Return to Employee List");
		System.out.println("I - Return to Main Menu.");
		
		String newStr = scan.nextLine();
		
		while(!(newStr.equalsIgnoreCase("A") || newStr.equalsIgnoreCase("B") || newStr.equalsIgnoreCase("C") || newStr.equalsIgnoreCase("D") || newStr.equalsIgnoreCase("E") || newStr.equalsIgnoreCase("F") || newStr.equalsIgnoreCase("G") || newStr.equalsIgnoreCase("H") || newStr.equalsIgnoreCase("I"))) {
			System.out.println("You have entered in: " + newStr + "\nPlease enter in either A, B, C, D, E, F, G, or H:");
			newStr = scan.nextLine();
		}
		
		Employee emp = Employee.findEmployeeById(index);
		
		if(newStr.equalsIgnoreCase("A")) {
			System.out.println("Enter a valid First Name: (a-z')");
			while(true) {
				String firstName = scan.nextLine();
				if(firstName.matches("^[a-zA-Z']+$")) {
					emp.setFirstName(firstName);
					break;
				}
			}
		} else if(newStr.equalsIgnoreCase("B")) {
			System.out.println("Enter a valid Last Name: (a-z')");
			while(true) {
				String lastName = scan.nextLine();
				if(lastName.matches("^[a-zA-Z']+$")) {
					emp.setLastName(lastName);
					break;
				}
			}
		} else if(newStr.equalsIgnoreCase("C")) {
			System.out.println("Enter an Email Address:");
			while(true) {
				String emailAddress = scan.nextLine();
				if(rv.isValidEmail(emailAddress)) {
					emp.setEmail(emailAddress);
					break;
				}
				System.out.println("Enter a valid Email Address: eg. noreply@domain.io");
			}
		} else if(newStr.equalsIgnoreCase("D")) {
			System.out.println("Enter in a Phone Number:");
			while(true) {
				String phoneNumber = scan.nextLine();
				if(rv.isValidPhoneNumber(phoneNumber)) {
					emp.setPhoneNumber(phoneNumber);
					break;
				}
				System.out.println("Enter a valid Phone Number: ###-###-####");
			}
		} else if(newStr.equalsIgnoreCase("E")) {
			while(true) {
				System.out.println("Enter a valid salary:");
				double salary = validDouble();
				emp.setSalary(salary);
				break;
			}
			
		} else if(newStr.equalsIgnoreCase("F")) {
			System.out.println("Pick an option (T or F)");
			System.out.println("T - this will make the employee the department head.");
			System.out.println("F - this will not make the employee the department head.");
			while(true) {
				String departmentHead = scan.nextLine();
				if(departmentHead.equalsIgnoreCase("T")) {
					Department deptHead = Department.findDepartmentById(emp.getDepartmentId());
					Employee employeeHead = Employee.findEmployeeById(deptHead.getEmployeeId());
					System.out.println(deptHead);
					System.out.println(employeeHead);

					if(deptHead != null && employeeHead != null) {
						System.out.println("IN LOOP!!!");
						deptHead.setEmployeeId(emp.getEmployeeId());
						employeeHead.setDepartmentHead(false);
						Department.updateDepartment(deptHead.getDepartmentId(), deptHead);
						Employee.updateEmployee(employeeHead.getEmployeeId(), employeeHead);
						System.out.println("EMPLOYEE: " + employeeHead.getFirstName() + employeeHead.getLastName() + " has been been demoted from the head of the department.");
						System.out.println("DEPARTMENT: " + deptHead.getName() + " has changed its employeeId to: " + deptHead.getEmployeeId());
					}
					emp.setDepartmentHead(true);
					break;
				} else if(departmentHead.equalsIgnoreCase("F")) {
					emp.setDepartmentHead(false);
					Department dept = Department.findDepartmentById(emp.getDepartmentId());
					Employee newHead = Employee.findEmployeeToBeNewHeadBySalary();
					if(newHead != null) {
						dept.setEmployeeId(newHead.getEmployeeId());
						newHead.setDepartmentHead(true);
						newHead.setDepartmentId(dept.getDepartmentId());
						Employee.updateEmployee(newHead.getEmployeeId(), newHead);
						Department.updateDepartment(dept.getDepartmentId(), dept);
						System.out.println("NEW DEPARTMENT HEAD: " + newHead.getFirstName() + " " + newHead.getLastName());
					}
					break;
				}
			}
			
		} else if(newStr.equalsIgnoreCase("G")) {
			System.out.println("Please pick from one of these departments: ");
			for(Department dept: Department.listDepartments()) {
				System.out.println(dept.getDepartmentId() + " - " + dept.getName());
			}
			Employee newHead = null;
			System.out.println();
			while(true) {
				System.out.println("Please pick from one of these departments: ");
				int deptInt = validInterger();
//				scan.nextLine();
				if(Department.findDepartmentById(deptInt) != null) {
					if(emp.isDepartmentHead() && emp.getDepartmentId() != deptInt) {
						newHead = Employee.findEmployeeToBeNewHeadBySalary();
						if(newHead == null) {
							System.out.println("There is a department without a head.");
							break;
						}
						System.out.println(emp.getFirstName() + " " + emp.getLastName() + " is no longer the department head.");
						System.out.println(newHead.getFirstName() + " " + newHead.getLastName() + " has replaced the position of department head.");
						emp.setDepartmentHead(false);
						newHead.setDepartmentHead(true);
						newHead.setDepartmentId(emp.getDepartmentId());
						Employee.updateEmployee(newHead.getEmployeeId(), newHead);
					}
					emp.setDepartmentId(deptInt);
					break;
				}
			}
			// if not head change id.
			// if head ask if you want to become the head also.
			// move other head to false.
		} else if(newStr.equalsIgnoreCase("H")) {
			viewEmployees();
		} else if(newStr.equalsIgnoreCase("I")) {
			mainMenu();
		}
		Employee.updateEmployee(index, emp);
		selectedViewEmployee(index);
	}
	
	private static void selectedViewDepartment(int index) {
		while(Department.findDepartmentById(index) == null) {
			System.out.println("Enter a valid id in the table: ");
			index = validInterger();
		}
		printDepartmentBanner();
		System.out.println(printDepartment(Department.findDepartmentById(index)));
		printDepartmentBannerLine();
		System.out.println();
		
		System.out.println("Pick an option (A, B, C, D, E, or F):");
		System.out.println("-------------------------------------------");
		System.out.println("A - Edit Name.");
		System.out.println("B - Edit Phone Number Extension.");
		System.out.println("C - Edit Budget");
		System.out.println("D - List all employees in the department.");
		System.out.println("E - Return to Department List");
		System.out.println("F - Return to Main Menu.");
		System.out.println("NOTE: Only employees can set there id in the department.");
		
		String newStr = scan.nextLine();
		
		while(!(newStr.equalsIgnoreCase("A") || newStr.equalsIgnoreCase("B") || newStr.equalsIgnoreCase("C") || newStr.equalsIgnoreCase("D") || newStr.equalsIgnoreCase("E") || newStr.equalsIgnoreCase("F"))) {
			System.out.println("You have entered in: " + newStr + "\nPlease enter in either A, B, C, D, E, or F:");
			newStr = scan.nextLine();
		}

		Department dept = Department.findDepartmentById(index);
		
		if(newStr.equalsIgnoreCase("A")) {
			while(true) {
				System.out.println("Enter a valid First Name: (a-z')");
				String name = scan.nextLine();
				if(name.matches("^[a-zA-Z']+$")) {
					dept.setName(name);
					break;
				}
			}
		} else if(newStr.equalsIgnoreCase("B")) {
			while(true) {
				String phoneExt;
				System.out.println("Enter in a 3 digit number for the phone extension.");
				try {
					phoneExt = validPhoneExtension(scan.nextLine());
					dept.setPhoneNumberExt(phoneExt);
					break;
				} catch(NotValidExtensionNumber ex) {
					System.out.println(ex);
				}
			}

		} else if(newStr.equalsIgnoreCase("C")) {
			while(true) {
				System.out.println("Enter a valid budget for the department:");
				double budget = validDouble();
				dept.setBudget(budget);
				break;
			}
		} else if(newStr.equalsIgnoreCase("D")) {
			printEmployeeBanner();
			for (Employee employee : Employee.findAllEmployeesByDepartment(dept)) {
				System.out.println(printEmployee(employee));
				printEmployeeBannerLine();
			}
			System.out.println();
			
		} else if(newStr.equalsIgnoreCase("E")) {
			viewDepartments();
		} else if(newStr.equalsIgnoreCase("F")) {
			mainMenu();
		}
		Department.updateDepartment(index, dept);
		selectedViewDepartment(index);
	}
	
	private static String validPhoneExtension(String newStr) throws NotValidExtensionNumber {
		if(newStr.matches("^[0-9]{3}$")) {
			return newStr;
		} else {
			throw new NotValidExtensionNumber(newStr);
		}
	}
	
	private static void addEmployee() {
		System.out.println("Please answer all prompts below: ");
		String firstName = "";
		String lastName = "";
		String email = "";
		String phoneNumber = "";
		double salary = 0;
		boolean departmentHead = false;
		int departmentId = 0;
		// First Name
		System.out.println("Enter a valid First Name: (a-z')");
		while(true) {
			firstName = scan.nextLine();
			if(firstName.matches("^[a-zA-Z']+$")) {
				break;
			}
		}
		// Last Name
		System.out.println("Enter a valid Last Name: (a-z')");
		while(true) {
			lastName = scan.nextLine();
			if(lastName.matches("^[a-zA-Z']+$")) {
				break;
			}
		}
		
		// Check for valid email.
		System.out.println("Enter an Email Address:");
		while(true) {
			email = scan.nextLine();
			if(rv.isValidEmail(email)) {
				break;
			}
			System.out.println("Enter a valid Email Address: eg. noreply@domain.io");
		}
		
		// Check for valid phone number.
		System.out.println("Enter in a Phone Number:");
		while(true) {
			phoneNumber = scan.nextLine();
			if(rv.isValidPhoneNumber(phoneNumber)) {
				break;
			}
			System.out.println("Enter a valid Phone Number: ###-###-####");
		}
		
		// Check for valid double.
		while(true) {
			System.out.println("Enter a valid salary:");
			salary = validDouble();
			break;
		}
		
		if(Department.listDepartments() != null) {
			// select department
			System.out.println("Please pick from one of these departments: ");
			for(Department dept: Department.listDepartments()) {
				System.out.println(dept.getDepartmentId() + " - " + dept.getName());
			}
			while(true) {
				System.out.println("Please pick from one of these departments: ");
				int deptInt = validInterger();
				Department dept = Department.findDepartmentById(deptInt);
				if(dept != null) {
					departmentId = dept.getDepartmentId();
					break;
				}
			}
			
			// check for T or F.
			System.out.println("Pick an option (T or F)");
			System.out.println("T - this will make the employee the department head.");
			System.out.println("F - this will not make the employee the department head.");
			while(true) {
				Department deptHead = Department.findDepartmentById(departmentId);
				if(Department.findAllEmployeesWorkingInDepartment(deptHead).size() == 0) {
					departmentHead = true;
					deptHead.setEmployeeId(Employee.getLastEmployeeId());
					Department.updateDepartment(deptHead.getDepartmentId(), deptHead);
					break;
				}
				String departmentHead1 = scan.nextLine();
				if(departmentHead1.equalsIgnoreCase("T")) {
					Employee employeeHead = Employee.findEmployeeById(deptHead.getEmployeeId());
					System.out.println(deptHead);
					System.out.println(employeeHead);

					if(deptHead != null && employeeHead != null) {
						System.out.println("IN LOOP!!!");
						deptHead.setEmployeeId(Employee.getLastEmployeeId());
						employeeHead.setDepartmentHead(false);
						Department.updateDepartment(deptHead.getDepartmentId(), deptHead);
						Employee.updateEmployee(employeeHead.getEmployeeId(), employeeHead);
						System.out.println("EMPLOYEE: " + employeeHead.getFirstName() + employeeHead.getLastName() + " has been been demoted from the head of the department.");
						System.out.println("DEPARTMENT: " + deptHead.getName() + " has changed its employeeId to: " + deptHead.getEmployeeId());
					}
					departmentHead = true;
					break;
				} else if(departmentHead1.equalsIgnoreCase("F")) {
					departmentHead = false;
					break;
				}
			}
		}
		
				
		// Add new Employee
		Employee.addEmployee(new Employee(Employee.getLastEmployeeId(), firstName, lastName, email, phoneNumber, salary, departmentHead, departmentId));
				
		 System.out.println("The Employee has been added to the database.");
			viewEmployees();
	}
	
	private static void addDepartment() {
		System.out.println("Please answer all prompts below: ");
//		System.out.println("Enter in the Department's name: ");
		String name = "";
		String phoneNumberExt = "";
		double budget = 0;
		while(true) {
			System.out.println("Enter a valid Department Name: (a-z')");
			name = scan.nextLine();
			if(name.matches("^[a-zA-Z']+$")) {
				break;
			}
		}
		while(true) {
			System.out.println("Enter in a 3 digit number for the phone extension.");
			try {
				phoneNumberExt = validPhoneExtension(scan.nextLine());
				break;
			} catch(NotValidExtensionNumber ex) {
				System.out.println(ex);
			}
		}
		while(true) {
			System.out.println("Enter a valid budget for the department:");
			budget = validDouble();
			break;
		}
		Department.addDepartment(new Department(Department.getLastDepartmentId(), name, -1, phoneNumberExt, budget));
		System.out.println("The Department has been added to the database.");
		viewDepartments();
	}
	
	private static void removeEmployee() {
		if(Employee.listEmployees().size() == 0) {
			for(Department dep: Department.listDepartments()) {
			 	dep.setEmployeeId(-1);
			 	Department.updateDepartment(dep.getDepartmentId(), dep);
			}
			System.out.println("Nothing to remove.");
			viewEmployees();
		}
		System.out.println("REMOVING EMPLOYEE: Enter a valid id:");
		int validInt = validInterger();
		Employee emp = null;
		boolean check = false;
		int deptInt = -1;
		
		while(true) {
			emp = Employee.findEmployeeById(validInt);
			if(emp != null) {
				deptInt = emp.getDepartmentId();
				check = emp.getDepartmentHead();
				Employee.removeEmployee(validInt);
				break;
			}
			System.out.println("FAILED TO REMOVE. Please enter an id in the table: ");
			validInt = validInterger();
		}
		if(check) {
			// Find need department head.
			Employee newHead = Employee.findEmployeeToBeNewHeadBySalary();
			System.out.println("SHOULD BE HERE");
			if(newHead != null) {
				newHead.setDepartmentHead(true);
				newHead.setDepartmentId(deptInt);
				Employee.updateEmployee(newHead.getEmployeeId(), newHead);		
				System.out.println("NEW DEPARTMENT HEAD: " + newHead.getFirstName() + " " + newHead.getLastName());
			} else {
				Department deptm = Department.findDepartmentById(deptInt);
				deptm.setEmployeeId(-1);
				Department.updateDepartment(deptInt, deptm);
			}
		}
		System.out.println("The Employee has been removed from the list.\n");
		viewEmployees();
	}
	
	private static void removeDepartment() {
		if(Department.listDepartments().size() == 0) {
			System.out.println("There are no more departments to delete.");
			viewDepartments();
		}
		System.out.println("REMOVING DEPARTMENT: Enter a valid id:");
		int validInt = validInterger();
		Employee emp = null;
		Department dept = null;
		boolean check = false;
		int deptInt = -1;
		
		while(true) {
			dept = Department.findDepartmentById(validInt);
			if(dept != null) {
				System.out.println("Successfully Removed the department with the name: " + dept.getName());
				Department.removeTheDepartment(validInt);
				viewDepartments();
				break;
			}
			System.out.println("FAILED TO REMOVE. Please enter an id in the table: ");
			validInt = validInterger();
		}
		
	}
	
	private static int validInterger() {
		while(true) {
			try {
				int newInt = scan.nextInt();
				return newInt;
			} catch(InputMismatchException ex) {
				System.out.println("Enter a valid integer: ");
				scan.nextLine();
			}
		}
	}
	
	private static double validDouble() {
		while(true) {
			try {
				double newDouble = scan.nextDouble();
				if(newDouble > 0) {
					return newDouble;					
				} else {
					System.out.println("Enter a positive double:");
				}
			} catch(InputMismatchException ex) {
				System.out.println("Enter a valid double: ");
				scan.nextLine();
			}
		}
	}

}
