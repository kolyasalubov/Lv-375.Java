package com.softserve.edu;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConcurrencyTest1 {
	private Map<String, String> data = new HashMap<String, String>();

	@Test(threadPoolSize = 3, invocationCount = 3)
	public void testMapOperations() throws Exception {
		data.put("1-" + Thread.currentThread().getId(),
				"data-" + Thread.currentThread().getId());
		for (Map.Entry<String, String> entry : data.entrySet()) {
			System.out.println(entry);
		}
		//Thread.sleep(1000);
		data.clear();
		//data = null;
	}
}