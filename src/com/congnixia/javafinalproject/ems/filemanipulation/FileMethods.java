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

import com.congnixia.javafinalproject.ems.models.Department;
import com.congnixia.javafinalproject.ems.models.Employee;

public class FileMethods {

	private static final File fileEmployee = new File("resources/employees.csv");
	private static final File fileDepartment = new File("resources/departments.csv");

	private static List<Object> employeeList = new ArrayList<Object>();
	private static List<Object> departmentList = new ArrayList<Object>();
	
	private static String employInt = "";
	private static String departInt = "";

	/**
	 * Checks to make sure both files exist. Otherwise creates the files. This
	 * should be called at the beginning of program execution.
	 * 
	 * @throws IOException
	 */
	private static void checkFiles() throws IOException {
		if (!fileEmployee.exists()) {
			fileEmployee.createNewFile();
		}
		if (!fileDepartment.exists()) {
			fileDepartment.createNewFile();
		}
	}
	
	public static void runOnce() throws IOException {
		checkFiles();
		if(fileEmployee.length() == 0) {
			FileMethods.addObjectList(FileDriver.makeTestDataEmployee());
		}
		if(fileDepartment.length() == 0) {
			FileMethods.addObjectList(FileDriver.makeTestDataDepartment());			
		}
		listObjects('e');
		listObjects('d');
	}

	/**
	 * Reads in file and makes objects of either Employee or Department and returns
	 * a List of that type of Object. If this this returns null check char
	 * parameter.
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static List<Object> listObjects(char obj) throws IOException {
		List<Object> objs = new ArrayList<Object>();
		String line = "";
		if (obj == 'e') {
			try (FileReader fr = new FileReader(fileEmployee); BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					Employee e = new Employee(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
							Double.parseDouble(values[5]), Boolean.parseBoolean(values[6]),
							Integer.parseInt(values[7]));
					objs.add(e);
					employeeList.add(e);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		} else if (obj == 'd') {
			try (FileReader fr = new FileReader(fileDepartment); BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					Department d = new Department(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]),
							Integer.parseInt(values[3]), Double.parseDouble(values[4]));
					objs.add(d);
					 departmentList.add(d);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}
		return objs;
	}

	/**
	 * This will add a single object of type Employee or Department. Returns true if
	 * object added.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean addObject(Object obj) {
		File file = null;
		Employee emp = null;
		Department dep = null;

		if (obj instanceof Employee) {
			emp = (Employee) obj;
			file = fileEmployee;
		} else if (obj instanceof Department) {
			dep = (Department) obj;
			file = fileDepartment;
		} else {
			System.out.println("Object is not recognized.");
			return false;
		}

		try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr);) {
			if (file.equals(fileEmployee)) {
				if (ReadingFiles.checkIfObjectExists(file)) {
					br.append("\n" + emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				} else {
					br.append(emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
							+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
							+ emp.isDepartmentHead() + "," + emp.getDepartmentId());
				}
				return true;
			} else if (file.equals(fileDepartment)) {
				if (ReadingFiles.checkIfObjectExists(file)) {
					br.append("\n" + dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + ","
							+ dep.getPhoneNumberExt() + "," + dep.getBudget());
				} else {
					br.append(dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + ","
							+ dep.getPhoneNumberExt() + "," + dep.getBudget());
				}
				return true;
			} else {
				System.out.println("Nothing was added.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean removeObject(int index, char c) throws IOException {
		if (c == 'e') {
			File file = new File("resources/employees.csv");
			file.delete();
			checkFiles();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee emp = (Employee) employeeList.get(i);
				if (emp.getEmployeeId() != index) {
					addObject(emp);
				}
			}
			return true;
		} else if (c == 'd') {
			File file = new File("resources/departments.csv");
			file.delete();
			checkFiles();
			for (int i = 0; i < departmentList.size(); i++) {
				Department dep = (Department) departmentList.get(i);
				if (dep.getDepartmentId() != index) {
					addObject(dep);
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean updateObject(int index, char c, Object obj) throws IOException {
		if (c == 'e') {
			File file = new File("resources/employees.csv");
			file.delete();
//			checkFiles();
			for (int i = 0; i < employeeList.size(); i++) {
				Employee emp = (Employee) employeeList.get(i);
				if (emp.getEmployeeId() != index) {
					addObject(emp);
				} else {
					addObject(obj);
				}
			}
			return true;
		} else if (c == 'd') {
			File file = new File("resources/departments.csv");
			file.delete();
//			checkFiles();
			for (int i = 0; i < departmentList.size(); i++) {
				Department dep = (Department) departmentList.get(i);
				if (dep.getDepartmentId() != index) {
					addObject(dep);
				}
			}
			return true;
		}
		return false;
	}
	
	public static int findLastOfEmployeeId() throws IOException {
		try (FileReader fr = new FileReader(fileEmployee); BufferedReader br = new BufferedReader(fr);) {
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
		try (FileReader fr = new FileReader(fileDepartment); BufferedReader br = new BufferedReader(fr);) {
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

	public static boolean addObjectList(List<Object> objs) {
		for (int i = 0; i < objs.size(); i++) {
			if (objs.get(i) instanceof Employee) {
				Employee emp = (Employee) objs.get(i);
				emp.setEmployeeId(emp.getEmployeeId() + i);
				addObject((Employee) objs.get(i));
			} else if (objs.get(i) instanceof Department) {
				Department dep = (Department) objs.get(i);
				dep.setDepartmentId(dep.getDepartmentId() + i);
				addObject((Department) objs.get(i));
			}
		}
		return false;
	}
	
}