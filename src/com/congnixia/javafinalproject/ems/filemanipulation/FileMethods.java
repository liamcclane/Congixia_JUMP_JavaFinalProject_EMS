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

//	private static List<Object> employeeList = new ArrayList<Object>();
//	private static List<Object> departmentList = new ArrayList<Object>();
	
	
	private static List<Employee> theEmployeeList = new ArrayList<Employee>();
	private static List<Department> theDepartmentList = new ArrayList<Department>();
	
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
			FileMethods.addEmployeeList(FileDriver.makeTestDataEmployee());
		}
		if(fileDepartment.length() == 0) {
			FileMethods.addDepartmentList(FileDriver.makeTestDataDepartment());			
		}
		listTheEmployees();
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
	
	public static boolean addEmployeeList(List<Employee> emp) {
		for (int i = 0; i < emp.size(); i++) {
			if (emp.get(i) instanceof Employee) {
				boolean check = false;
				Employee emp1 = (Employee) emp.get(i);
				emp1.setEmployeeId(emp1.getEmployeeId() + i);
				check = addTheEmployee(emp.get(i));
				if(check == false) {
					System.out.println("AddEmployeeList Check this out.");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean addDepartmentList(List<Department> dep) {
		for (int i = 0; i < dep.size(); i++) {
			if (dep.get(i) instanceof Department) {
				boolean check = false;
				Department depCast = (Department) dep.get(i);
				depCast.setDepartmentId(depCast.getDepartmentId() + i);
				check = AddTheDepartment(dep.get(i));
				if(check == false) {
					System.out.println("AddDepartmentList Check this out.");
					return false;
				}
			}
		}
		return true;
	}
	
	public static List<Employee> listTheEmployees() throws IOException {
		theEmployeeList.clear();
		try (FileReader fr = new FileReader(fileEmployee); BufferedReader br = new BufferedReader(fr);) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if(!line.equals("")) {
					String[] values = line.split(",");
					Employee e = new Employee(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
							Double.parseDouble(values[5]), Boolean.parseBoolean(values[6]),
							Integer.parseInt(values[7]));
					theEmployeeList.add(e);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return theEmployeeList;
	}

	public static List<Department> listTheDepartments() throws IOException {
		theDepartmentList.clear();
		try (FileReader fr = new FileReader(fileDepartment); BufferedReader br = new BufferedReader(fr);) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if(!line.equals("")) {
					String[] values = line.split(",");
					Department d = new Department(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]), values[3], Double.parseDouble(values[4]));
					theDepartmentList.add(d);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return theDepartmentList;
	}
	

	public static boolean addTheEmployee(Employee emp) {
		try (FileWriter fr = new FileWriter(fileEmployee, true); BufferedWriter br = new BufferedWriter(fr);) {
			br.append(emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + ","
					+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
					+ emp.isDepartmentHead() + "," + emp.getDepartmentId() + "\n");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean AddTheDepartment(Department dep) {
		try (FileWriter fr = new FileWriter(fileDepartment, true); BufferedWriter br = new BufferedWriter(fr);) {
			br.append(dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + "," + dep.getPhoneNumberExt() + "," + dep.getBudget() + "\n");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean removeTheEmployee(int index) throws IOException {
		List<Employee> listOfEmployees = listTheEmployees();
		try (FileWriter fr = new FileWriter(fileEmployee, false); BufferedWriter br = new BufferedWriter(fr);) {
			int count = 0;
			for (int i = 0; i < listOfEmployees.size(); i++) {
				if(listOfEmployees.get(i).getEmployeeId() == index) {
					count++;
				}
				if (listOfEmployees.get(i).getEmployeeId() != index) {
					if (fileEmployee.length() != 0) {
						br.append("\n" + listOfEmployees.get(i).getEmployeeId() + "," + listOfEmployees.get(i).getName() + "," + listOfEmployees.get(i).getEmail() + ","
								+ listOfEmployees.get(i).getPhoneNumber() + "," + listOfEmployees.get(i).getHireDate() + "," + listOfEmployees.get(i).getSalary() + ","
								+ listOfEmployees.get(i).isDepartmentHead() + "," + listOfEmployees.get(i).getDepartmentId() + "\n");
					} else {
						br.append(listOfEmployees.get(i).getEmployeeId() + "," + listOfEmployees.get(i).getName() + "," + listOfEmployees.get(i).getEmail() + ","
								+ listOfEmployees.get(i).getPhoneNumber() + "," + listOfEmployees.get(i).getHireDate() + "," + listOfEmployees.get(i).getSalary() + ","
								+ listOfEmployees.get(i).isDepartmentHead() + "," + listOfEmployees.get(i).getDepartmentId() + "\n");
					}
				}
			}
			if(count != 1) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean removeTheDepartment(int index) throws IOException {
		List<Department> listOfDepartments = listTheDepartments();
		try (FileWriter fr = new FileWriter(fileDepartment, false); BufferedWriter br = new BufferedWriter(fr);) {
			int count = 0;
			for (int i = 0; i < listOfDepartments.size(); i++) {
				if(listOfDepartments.get(i).getDepartmentId() == index) {
					count++;
				}
				if (listOfDepartments.get(i).getEmployeeId() != index) {
					if (fileEmployee.length() != 0) {
						br.append("\n" +  listOfDepartments.get(i).getDepartmentId() + "," + listOfDepartments.get(i).getName() + "," + listOfDepartments.get(i).getEmployeeId() + "," + listOfDepartments.get(i).getPhoneNumberExt() + "," + listOfDepartments.get(i).getBudget() + "/n");
					} else {
						br.append(listOfDepartments.get(i).getDepartmentId() + "," + listOfDepartments.get(i).getName() + "," + listOfDepartments.get(i).getEmployeeId() + "," + listOfDepartments.get(i).getPhoneNumberExt() + "," + listOfDepartments.get(i).getBudget() + "/n");
					}
				}
			}
			if(count != 1) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean updateTheEmployee(int index, Employee emp) throws IOException {
		List<Employee> listOfEmployees = listTheEmployees();
		int count = 0;
//		if(listOfEmployees.stream().filter(x -> x.getEmployeeId() == index && x.getEmployeeId() == emp.getEmployeeId()) == null) {
//			return false;
//		}
		for (int x = 0; x < listOfEmployees.size(); x++) {
			if(index == listOfEmployees.get(x).getEmployeeId() && emp.getEmployeeId() == listOfEmployees.get(x).getEmployeeId()) {
				count++;
			}
		}
		if(count == 0) {
			return false;
		}
		try (FileWriter fr = new FileWriter(fileEmployee, false); BufferedWriter br = new BufferedWriter(fr);) {
			for (int i = 0; i < listOfEmployees.size(); i++) {
				
				if (listOfEmployees.get(i).getEmployeeId() != emp.getEmployeeId()) {
					if (fileEmployee.length() != 0) {
						br.append("\n" + listOfEmployees.get(i).getEmployeeId() + "," + listOfEmployees.get(i).getName() + "," + listOfEmployees.get(i).getEmail() + ","
								+ listOfEmployees.get(i).getPhoneNumber() + "," + listOfEmployees.get(i).getHireDate() + "," + listOfEmployees.get(i).getSalary() + ","
								+ listOfEmployees.get(i).isDepartmentHead() + "," + listOfEmployees.get(i).getDepartmentId() + "\n");
					} else {
						br.append(listOfEmployees.get(i).getEmployeeId() + "," + listOfEmployees.get(i).getName() + "," + listOfEmployees.get(i).getEmail() + ","
								+ listOfEmployees.get(i).getPhoneNumber() + "," + listOfEmployees.get(i).getHireDate() + "," + listOfEmployees.get(i).getSalary() + ","
								+ listOfEmployees.get(i).isDepartmentHead() + "," + listOfEmployees.get(i).getDepartmentId() + "\n");
					}
				}
				else {
					if (fileEmployee.length() != 0) {
						br.append("\n" + index + "," + emp.getName() + "," + emp.getEmail() + ","
								+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
								+ emp.isDepartmentHead() + "," + emp.getDepartmentId() + "\n");
					} else {
						br.append(index + "," + emp.getName() + "," + emp.getEmail() + ","
								+ emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + ","
								+ emp.isDepartmentHead() + "," + emp.getDepartmentId() + "\n");
					}
				}
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateTheDepartment(int index, Department dep) throws IOException {
		List<Department> listOfDepartments = listTheDepartments();
		int count = 0;

		for (int x = 0; x < listOfDepartments.size(); x++) {
			if(index == listOfDepartments.get(x).getDepartmentId() && dep.getDepartmentId() == listOfDepartments.get(x).getDepartmentId()) {
				count++;
			}
		}
		if(count == 0) {
			return false;
		}
		try (FileWriter fr = new FileWriter(fileDepartment, false); BufferedWriter br = new BufferedWriter(fr);) {
			for (int i = 0; i < listOfDepartments.size(); i++) {
				
				if (listOfDepartments.get(i).getDepartmentId() != dep.getDepartmentId()) {
					if (fileDepartment.length() != 0) {
						br.append("\n" +  listOfDepartments.get(i).getDepartmentId() + "," + listOfDepartments.get(i).getName() + "," + listOfDepartments.get(i).getEmployeeId() + "," + listOfDepartments.get(i).getPhoneNumberExt() + "," + listOfDepartments.get(i).getBudget() + "/n");
					} else {
						br.append(listOfDepartments.get(i).getDepartmentId() + "," + listOfDepartments.get(i).getName() + "," + listOfDepartments.get(i).getEmployeeId() + "," + listOfDepartments.get(i).getPhoneNumberExt() + "," + listOfDepartments.get(i).getBudget() + "/n");
					}
				}
				else {
					if (fileDepartment.length() != 0) {
						br.append("\n" + index + "," + dep.getName() + "," + dep.getEmployeeId() + "," + dep.getPhoneNumberExt() + "," + dep.getBudget());
					} else {
						br.append(index + "," + dep.getName() + "," + dep.getEmployeeId() + "," + dep.getPhoneNumberExt() + "," + dep.getBudget());
					}
				}
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}