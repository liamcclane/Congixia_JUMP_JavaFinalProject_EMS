package com.congnixia.javafinalproject.ems.filemanipulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.congnixia.javafinalproject.ems.models.*;

public class ReadingFiles {

    public static void writeToFile(Object obj) throws IOException {
		
		File file = null;
		Employee emp = null;
		Department dep = null;
		
		if(obj instanceof Employee) {
			
			emp = (Employee) obj;
			file = new File("resources/employee.csv");
			
		} else if(obj instanceof Department) {
			
			dep = (Department) obj;
			file = new File("resources/department.csv");
			
		} else {
			System.out.println("Object is not recognized.");
			return;
		}
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		try (FileWriter fr = new FileWriter(file, true); BufferedWriter br = new BufferedWriter(fr);) {
			if(file.getName().equals("resources/employee.csv")) {
                br.append("\n" + emp.getEmployeeId() + "," + emp.getName() + "," + emp.getEmail() + "," + emp.getPhoneNumber() + "," + emp.getHireDate() + "," + emp.getSalary() + "," + emp.isDepartmentHead() + "," + emp.getDepartmentId());
			} else {
                br.append("\n" + dep.getDepartmentId() + "," + dep.getName() + "," + dep.getEmployeeId() + "," + dep.getPhoneNumberExt() + "," + dep.getBudget());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
