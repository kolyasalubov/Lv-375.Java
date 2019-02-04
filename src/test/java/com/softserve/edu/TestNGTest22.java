package com.softserve.edu;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTest22 {

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t\tClass TestNGTest21: @BeforeMethod beforeMethod()");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult testResult) {
		if (!testResult.isSuccess()) {
			// TODO Create Report
			System.out.println("\t\t\t\t***Class TestNGTest21: Test Failed + " + testResult.getName());
		}
		System.out.println("\t\t\t\tClass TestNGTest21: @AfterMethod afterMethod()");
	}

	@Test
	public void checkFirst1() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkFirst1()");
		// int i = 1 / 0;
		// Assert.assertEquals(5, 2 + 3 + 1);
	}

	@Test
	public void checkFirst2() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkFirst2()");
		int i = 1 / 0;
		// Assert.assertEquals(5, 2 + 3 + 1);
	}

	@Test
	public void checkFirst3() {
		System.out.println("\t\t\t\t\tClass StartTest: @Test checkFirst3()");
		// int i = 1 / 0;
		Assert.assertEquals(5, 2 + 3 + 1);
	}

}
