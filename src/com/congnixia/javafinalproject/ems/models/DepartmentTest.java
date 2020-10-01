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
	public void testDepartmentGetter() {
		Department testDepartment = new Department("HR", 0, 40000);
		assertEquals("HR", testDepartment.getType());
		assertEquals(0, testDepartment.getPhoneNumberExt());
		assertEquals(40000, testDepartment.getBudget());
	}
	
	@Test
	public void testDepartmentSetter() {
		Department testDepartment = new Department("HR", 0, 40000);
		testDepartment.setType("Sales");
		testDepartment.setPhoneNumberExt(1);
		testDepartment.setBudget(30000);
		assertEquals("Sales", testDepartment.getType());
		assertEquals(1, testDepartment.getPhoneNumberExt());
		assertEquals(30000, testDepartment.getBudget());
	}

}
