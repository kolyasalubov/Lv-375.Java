package com.softserve.edu;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTest21 {
	private boolean isComplete;

	@BeforeMethod
	public void beforeMethod() {
		isComplete = false;
		System.out.println("\t\t\t\tClass TestNGTest21: @BeforeMethod beforeMethod()");
	}

	@AfterMethod
	public void afterMethod() {
		if (!isComplete) {
			// TODO Create Report
			System.out.println("\t\t\t\tClass TestNGTest21: Test Failed");
		}
		System.out.println("\t\t\t\tClass TestNGTest21: @AfterMethod afterMethod()");
	}

	@Test
	public void checkFirst() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkFirst()");
		//int i = 1 / 0;
		// Assert.assertEquals(5, 2 + 3 + 1);
		isComplete = true;
	}

}
