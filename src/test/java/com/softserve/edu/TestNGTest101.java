package com.softserve.edu;

import org.testng.annotations.Test;

public class TestNGTest101 {

	@Test(priority = 2)
	public void method1() {
		System.out.println("This is method 1");
		//throw new RuntimeException("Ha-Ha-HA");
	}

	@Test(priority = 3)
	public void method2() {
		System.out.println("This is method 2");
	}

	@Test(priority = 1)
	public void method3() {
		System.out.println("This is method 3");
	}

	@Test(priority = 1)
	public void method4() {
		System.out.println("This is method 4");
	}

}
