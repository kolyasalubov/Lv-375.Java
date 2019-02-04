package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGTest23 {

	@Test
	public void checkFirst() {
		System.out.println("Class TestNGTest23: @Test checkFirst() START");
		//
		System.out.println("\n\tAssert.assertEquals(2 + 3 + 1, 5); START");
		Assert.assertEquals(2 + 3, 5);
		System.out.println("\tAssert.assertEquals(2 + 3 + 1, 5); DONE");
		//
		System.out.println("\n\tAssert.assertEquals(2 + 3, 5); START");
		Assert.assertEquals(2 + 3, 5);
		System.out.println("\tAssert.assertEquals(2 + 3, 5); DONE");
		//
		System.out.println("Class TestNGTest23: @Test checkFirst() DONE");
	}

}
