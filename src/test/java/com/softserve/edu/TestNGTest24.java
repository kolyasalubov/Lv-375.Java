package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGTest24 {
	private SoftAssert softAssert = new SoftAssert();

	// @BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t\tClass TestNGTest21: @BeforeMethod beforeMethod()");
	}

	// @AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("\t\t\t\tClass TestNGTest21: @AfterMethod afterMethod()");
	}

	@Test
	public void checkFirst() {
		System.out.println("Class TestNGTest24: @Test checkFirst() START");
		//
		System.out.println("\n\tAssert.assertEquals(2 + 3 + 1, 5); START");
		softAssert.assertEquals(2 + 3 + 1, 5);
		System.out.println("\tAssert.assertEquals(2 + 3 + 1, 5); DONE");
		//
		System.out.println("\n\tAssert.assertEquals(2 + 3, 5); START");
		softAssert.assertEquals(2 + 3, 5);
		System.out.println("\tAssert.assertEquals(2 + 3, 5); DONE");
		//
		System.out.println("Class TestNGTest23: @Test checkFirst() DONE");
		softAssert.assertAll();
	}

}
