package com.softserve.edu;

import javax.management.RuntimeErrorException;

import org.testng.annotations.Test;

public class TestNGTest10 {

	@Test(dependsOnMethods = { "method3" })
	public void method1() {
		System.out.println("This is method 1");
		//throw new RuntimeException("Ha-Ha-HA");
	}

	@Test(dependsOnMethods = { "method1", "method3" })
	public void method2() {
		System.out.println("This is method 2");
	}

	@Test
	public void method3() {
		System.out.println("This is method 3");
	}

}