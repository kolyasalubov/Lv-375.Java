package com.softserve.edu;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGTest61 {
	
	@BeforeClass
    public void beforeClass(ITestContext context) {
		HashMap<String, String> allParameters = new HashMap<String, String>(
				context.getCurrentXmlTest().getAllParameters());
		for (String key : allParameters.keySet()) {
			System.out.println("*** parameter: key=" + key + " value=" + allParameters.get(key));
		}
		System.out.println("@BeforeClass done");
    }

	@Test
	public void parameterIntTest() {
		System.out.println("parameterIntTest() done ");
	}
}
