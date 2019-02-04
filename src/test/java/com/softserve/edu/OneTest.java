
package com.softserve.edu;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OneTest {

	//@BeforeSuite
	public void beforeSuite() {
		System.out.println("\t@Class OneTest: BeforeSuite beforeSuite()");
	}

	//@AfterSuite
	public void afterSuite() {
		System.out.println("\tClass OneTest: @AfterSuite afterSuite()");
	}

	//@BeforeTest
	public void beforeTest() {
		System.out.println("\t\tClass OneTest: @BeforeTest beforeTest()");
	}

	//@AfterTest
	public void afterTest() {
		System.out.println("\t\tClass OneTest: @AfterTest afterTest()");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t\tClass OneTest: @BeforeClass beforeClass()");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("\t\t\tClass OneTest: @AfterClass afterClass()");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t\tClass OneTest: @BeforeMethod beforeMethod()");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t\tClass OneTest: @AfterMethod afterMethod()");
	}

	@Test
	public void checkFirst2() {
		System.out.println("\t\t\t\t\tClass OneTest: @Test checkFirst2()");
	}

	@Test
	public void checkSecond2() {
		System.out.println("\t\t\t\t\tClass OneTest: @Test checkSecond2()");
	}

}
