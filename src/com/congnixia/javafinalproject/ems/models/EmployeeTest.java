package com.congnixia.javafinalproject.ems.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	
	/**
	 * 
	 */	
	@Test
	public void findEmployeeOutOfBounds() {
		int id = 1000;
		Employee emp = Employee.findEmployeeById(id);
		assertNull(emp);
	}
	
	@Test
	public void findEmployee() {
		int id = 1;
		Employee emp = Employee.findEmployeeById(id);
		if(emp != null) {
			assertEquals(id, emp.getEmployeeId());
		} else {
			assertNull(emp);
		}
	}
	
	@Test 
	public void findEmployeeByFirstName() {
		String name = "Joe";
		List<Employee> emps = Employee.findAllEmployeesByFirstName(name);
		for (Employee employee : emps) {
			assertEquals(name, employee.getFirstName());
		}
	}

}
