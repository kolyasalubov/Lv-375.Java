package com.softserve.edu.mockito;

import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.dao.IProductDao;
import com.softserve.edu.service.ProductService;

public class UnitProductTest {

	@Test
	public void checkLastDigits() {
		// IProductDao productDao = Mockito.mock(ProductDao.class);
		// IProductDao productDao = Mockito.spy(new IProductDao());
		IProductDao productDao = PowerMockito.mock(IProductDao.class);
		//
		// PowerMockito.whenNew(StartOpr.class).withNoArguments().thenReturn(startOpr);
		// PowerMockito.whenNew(StartOpr.class).withNoArguments().thenReturn(PowerMockito.mock(StartOpr.class));
		PowerMockito.when(productDao.getIPAddress()).thenReturn(".123");
		// Mockito.doCallRealMethod().when(userDao).getIPAddress("");
		ProductService productService = new ProductService(productDao);
		String actual;
		String expected;
		//
		expected = "123";
		actual = productService.getLastDigits();
		// actual = productService.getLastDigits("");
		//
		Assert.assertEquals(actual, expected, "LastDigits ERROR");
	}

}