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

public class StartTest {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("\t@Class StartTest: BeforeSuite beforeSuite()");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("\tClass StartTest: @AfterSuite afterSuite()");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("\t\tClass StartTest: @BeforeTest beforeTest()");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("\t\tClass StartTest: @AfterTest afterTest()");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t\tClass StartTest: @BeforeClass beforeClass()");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("\t\t\tClass StartTest: @AfterClass afterClass()");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t\tClass StartTest: @BeforeMethod beforeMethod()");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t\tClass StartTest: @AfterMethod afterMethod()");
		//throw new RuntimeException("HaHaHa");
	}

	@Test
	public void checkFirst() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkFirst()");
	}

	@Test
	public void checkSecond() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkSecond()");
	}

}
