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

import com.congnixia.javafinalproject.ems.models.*;

public class ReadingFiles {

	private static String employInt = "";
	private static String departInt = "";

	/**
	 * this function reads the file "resources/employee.csv" and returns a List if
	 * Employee objects
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<Employee> readEmployees() throws IOException {

		List<Employee> allEmployees = new ArrayList<Employee>();
		File file = new File("resources/employees.csv");

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

	public static List<Department> readDepartments() throws IOException {

		List<Department> allDepartments = new ArrayList<Department>();
		File file = new File("resources/departments.csv");

		if (!file.exists()) {
			file.createNewFile();
		}

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");

				Department e = new Department(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]),
						Integer.parseInt(values[3]), Double.parseDouble(values[4]));
				allDepartments.add(e);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return allDepartments;
	}

	/**
	 * Iterates through a list of objects. Determines what instanceof the object is.
	 * Runs through the writeFileMethod.
	 * 
	 * @param objs
	 * @throws IOException
	 */
	public static void writeAllToFile(List<Object> objs) throws IOException {
		for (int i = 0; i < objs.size(); i++) {
			if (objs.get(i) instanceof Employee) {
				Employee emp = (Employee) objs.get(i);
				emp.setEmployeeId(emp.getEmployeeId() + i);
				writeToFile((Employee) objs.get(i));
			} else if (objs.get(i) instanceof Department) {
				Department dep = (Department) objs.get(i);
				dep.setDepartmentId(dep.getDepartmentId() + i);
				writeToFile((Department) objs.get(i));
			} else {
				System.out.println("Fix this!!");
			}
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static int findLastOfEmployeeId() throws IOException {
		File file = new File("resources/employees.csv");
		if (!file.exists()) {
			file.createNewFile();
			return 0;
		}

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");
				employInt = values[0];
			}
			if (employInt == "") {
				return 0;
			}
			return Integer.parseInt(employInt);

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		return 0;
	}

	public static int findLastOfDepartmentId() throws IOException {
		File file = new File("resources/departments.csv");
		if (!file.exists()) {
			file.createNewFile();
			return 0;
		}

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");
				departInt = values[0];
			}
			if (departInt == "") {
				return 0;
			}
			return Integer.parseInt(departInt);

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		return 0;
	}

	public static boolean checkIfObjectExists(File file) throws IOException {

		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {

			String line = br.readLine();
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
			file = new File("resources/employees.csv");

		} else if (obj instanceof Department) {

			dep = (Department) obj;
			file = new File("resources/departments.csv");

		} else {
			System.out.println(obj.toString());
			System.out.println("Object is not recognized.");
			return;
		}

		try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr);) {
			if (file.getName().equals("employees.csv")) {
				if (ReadingFiles.checkIfObjectExists(file)) {
					br.append("\n" + emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				} else {
					br.append(emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				}
			} else if (file.getName().equals("departments.csv")) {
				if (ReadingFiles.checkIfObjectExists(file)) {
					br.append("\n" + dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + ","
							+ dep.getPhoneNumberExt() + "," + dep.getBudget());
				} else {
					br.append(dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + ","
							+ dep.getPhoneNumberExt() + "," + dep.getBudget());
				}
			} else {
				System.out.println("Nothing was added.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
