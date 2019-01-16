package com.softserve.edu;

import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void testApp1() throws Exception {
		System.out.println("surefire.reports.directory = " + System.getProperty("surefire.reports.directory"));
		System.out.println("System.getenv().database.password = " + System.getenv().get("DATABASE_PASSWORD"));
	}

}
