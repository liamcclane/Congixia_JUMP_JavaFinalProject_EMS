package com.congnixia.javafinalproject.ems.filemanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.cognixia.jump.advancedjava.projects.Department;
//import com.cognixia.jump.advancedjava.projects.Employee;
import com.congnixia.javafinalproject.ems.models.*;

public class ReadingFiles {

	private static String lastInt = "";

	/**
	 * this function reads the file "resources/employee.csv"
	 * and returns a List if Employee objects
	 * @return
	 * @throws IOException
	 */
	public static List<Employee> readEmployee() throws IOException {

		List<Employee> allEmployees = new ArrayList<Employee>();
		File file = new File("resources/employee.csv");

		if (!file.exists()) {
			file.createNewFile();
		}

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");

				Employee e = new Employee(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
						Double.parseDouble(values[5]), Boolean.parseBoolean(values[6]), Integer.parseInt(values[7]));
				allEmployees.add(e);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return allEmployees;
	}

<<<<<<< HEAD
=======
	/**
	 * 
	 * @param objs
	 * @throws IOException
	 */
>>>>>>> 0f8d3c90ea26822bf7838b7b20748aebcdb0e1ee
	public static void writeAllToFile(List<Object> objs) throws IOException {
		for (int i = 0; i < objs.size(); i++) {
			System.out.println(objs.get(i));
			writeToFile(objs.get(i));
		}
	}

<<<<<<< HEAD
=======

	/** 
	 * @return
	 * @throws IOException
	 */
>>>>>>> 0f8d3c90ea26822bf7838b7b20748aebcdb0e1ee
	public static int findLastOfEmployeeId() throws IOException {
		File file = new File("resources/employee.csv");
		if (!file.exists()) {
			file.createNewFile();
			return 0;
		}

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");
				lastInt = values[0];
			}
			if (lastInt == "") {
				return 0;
			}
			return Integer.parseInt(lastInt);

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		return 0;
	}

//	public static boolean writeEmployee(Employee e) throws IOException {
//
//		File file = new File("resources/readfile.csv");
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//
//		try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr);) {
//			br.append("\n" + e.getName() + "," + e.getId() + "," + e.getSalary());
//			return true;
//		} catch (Exception ex) {
//			// TODO: handle exception
//			ex.printStackTrace();
//			return false;
//		}
//	}

	public static boolean checkIfObjectExists(File file) throws IOException {

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = br.readLine();
			System.out.println(line);
			if (line != null) {
				return true;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return false;
	}

	public static void writeToFile(Object obj) throws IOException {

		File file = null;
		Employee emp = null;
		Department dep = null;

		if (obj instanceof Employee) {
			emp = (Employee) obj;
			file = new File("resources/employee.csv");
			System.out.println("New Employee");

		} else if (obj instanceof Department) {

			dep = (Department) obj;
			file = new File("resources/department.csv");

		} else {
			System.out.println(obj.toString());
			System.out.println("Object is not recognized.");
			return;
		}

		try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr);) {
			if (file.getName().equals("employee.csv")) {
				if (ReadingFiles.checkIfObjectExists(file)) {
					br.append("\n" + emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				} else {
					br.append(emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				}
				System.out.println("Added new emp");
			} else if (file.getName().equals("employee.csv")) {
//				if (ReadingFiles.checkIfObjectExists(file)) {
//					br.append("\n" + emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
//							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
//							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
//				} else {
//					br.append(emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
//							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
//							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
//				}
//				System.out.println("Added new emp");
			} else {
				System.out.println("No new emp added");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
