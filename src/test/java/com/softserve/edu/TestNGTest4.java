package com.softserve.edu;

import org.testng.annotations.Test;

public class TestNGTest4 {
	
	@Test(timeOut = 2000)
	public void infinity() {
		long currentTime = System.currentTimeMillis();
		long nextTime = currentTime;
		long printTime = currentTime;
		while (nextTime - currentTime < 2500) {
			nextTime = System.currentTimeMillis();
			if (nextTime - printTime > 100) {
				System.out.println("Working ... " + (nextTime - currentTime));
				printTime = nextTime;
			}
		}
	}
}
