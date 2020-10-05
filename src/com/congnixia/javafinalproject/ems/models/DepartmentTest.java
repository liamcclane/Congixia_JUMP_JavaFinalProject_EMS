package com.congnixia.javafinalproject.ems.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentTest {

	/**
	 * Hi Josh, can you
	 * 
	 *  create logic to make sure that we do not 
	 *  try to make two people the department head
	 *  	* maybe make a custom exception 
	 *  
	 *  
	 *  test and verify all the getters and setters
	 * 
	 * 
	 * I am not sure where this test would go,
	 * maybe on both files, but 
	 * 
	 * 	check to make sure we are dethrowning and throwing the properly 
	 * 	the department head
	 * 
	 * 	check to make sure an newly added employee was added to the 
	 * 	allEmployees array in the departments object
	 * 
	 */
	@Test
	public void testDepartmentGetters() {
		Department testDepartment = new Department(0, "HR", 0, "40000", 0);
		assertEquals("HR", testDepartment.getName());
		assertEquals(0, testDepartment.getPhoneNumberExt());
		assertEquals(40000, testDepartment.getBudget());
	}
	
	@Test
	public void testDepartmentSetters() {
		Department testDepartment = new Department(0, "HR", 0, "40000", 0);
		testDepartment.setName("Sales");
		testDepartment.setPhoneNumberExt("1");
		testDepartment.setBudget(30000);
		assertEquals("Sales", testDepartment.getName());
		assertEquals(1, testDepartment.getPhoneNumberExt());
		assertEquals(30000, testDepartment.getBudget());
	}
	
	@Test
	public void testDepartmentFindByName() {
		String s = "Sales";
		Department d = Department.findDepartmentByName(s);
		assertEquals(d.getName(), s);
	}

	
	

}
