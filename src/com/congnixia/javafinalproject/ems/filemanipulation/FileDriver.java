package com.congnixia.javafinalproject.ems.filemanipulation;
import com.congnixia.javafinalproject.ems.models.*;

public class FileDriver {

	
	/**
	 * Hi Daniel, 
	 * 
	 * 
	 * Goals
	 * 	* we want an allEmployees.java file that is an ArrayList of all the employee objects
	 *  * we want a departmentHeads.java file of all the department Heads
	 *  	// lower priority	
	 *  	* we (might) want a allDepartments.java file of all the Department objects that are made...
	 *  
	 * 	* can you create a file named allEmployees.txt file that stores like this, 
	 * 		with tabs in between them
	 * 		and Capital H for head of Department A for associate
	 * 		Name/id/departmentType/isHead/phoneNubmer/extensionNumber
	 * 
	 * allEmployees.txt 
	 * 	 ___________________________________________________________________
	 * 	|	Joe			101			Sales		A		(123)456789 	222	|
	 * 	|	Bob			102			Sales		A		(123)456789 	222	|
	 * 	|	Bill		103			Sales		A		(123)456789 	222	|
	 * 	|	Ralph		104			Sales		A		(123)456789 	222	|
	 * 	|	James		105			Sales		A		(123)456789 	222	|
	 * 	|	Jessica		106			Sales		H		(123)456789 	222	|
	 * 	|	Peter		107			Sales		A		(123)456789 	222	|
	 * 	|	Penny		108			Electronics	A		(123)456789 	222	|
	 * 	|	Jenny		109			Electronics	A		(123)456789 	222	|
	 *  |	Ellie		110			Electronics	H		(123)456789 	222	|
	 * 	|	Jared		111			IT			H		(123)456789 	222	|
	 * 	|	Jordan		112			HQ			H		(123)456789 	222	|
	 * 	|																	|
	 * 
	 *	(this is secondary)
	 *	allDepartments.txt
	 * 	 ___________________________________________________________________
	 * 	|																	|
	 * 	|	Sales		Jesica		(123)456789 	222	                    |
	 * 	|	Electronics Ellie		(123)456789 	343	                    |
	 * 	|	HQ 			Jared 		(123)456789 	965	                    |
	 * 	|	IT 			Jordan 		(123)456789 	155	                    |

	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {

		
		
	}

	public static void makeTestData() {
		Department[] departments = {new Department("Sales", 123, 222), new Department("Electronics", 123, 343), new Department("HQ", 123, 965), new Department("IT", 123, 155)};

		Employee[] employees = {new Employee("Joe", "123", departments[0]), new Employee("Bob", "123", departments[0]), new Employee("Bill", "123", departments[0])};
	}
}

// employeeid, name, personalID, DepartmentID, isDepartmentHead, PhoneNumber
// departmentID, name, personalID, Phone Ext



/*
 *	employeeId	name	email				phoneNumber		hireDate	salary		headId	departmentId
 * 		1		Joe		joe@google.com		123 555 0000	1990-08-27	55000.00	F		222
 * 		2		Bob		bob@google.com		123 555 0001	1991-01-05 	15000.00	F		222
 * 		3		Bill	bill@google.com		123 555 0002	1994-04-16 	25000.00	F		222
 * 		4		Ralph	ralph@google.com	123 555 0003	1995-05-08 	10000.00	F		222
 * 		5		James	james@google.com	123 555 0004	1998-05-12 	20000.00	F		222
 * 		6		Jessica	jessica@google.com	123 555 0005	1999-01-09  100000.00	T		222
 * 		7		Peter	peter@google.com	123 555 0006	2003-12-09	90000.00	F		222
 * 		8		Penny	penny@google.com	123 555 0007	2005-08-11	65000.00	F		343
 * 		9		Jenny	jenny@google.com	123 555 0008	2007-14-22 	34000.00	F		343
 * 		10		Ellie	ellie@google.com	123 555 0009	2008-19-21 	76000.00	T		343
 * 		11		Jared	jared@google.com	123 555 0010	2008-02-01 	12000.00	T		965
 * 		12		Jordan	jordan@google.com	123 555 0011	2019-08-16 	0.50		T		155
 * /
 * 
 * 	departmentId	name			employeeId	phoneExtension 	budget
 * 	222				Sales			6			111				1000000.00
 * 	343				Electronics		10			222				125000.00
 * 	965				HQ				11			333				1500000.00
 * 	155				IT				12			444				375000.00
 * /