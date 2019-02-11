package com.softserve.edu;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonService;

@TestExecutionListeners(MockitoTestExecutionListener.class)
@SpringBootTest
public class PersonServiceTest {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Test
	public void testSomethingOnWidgetService() {
		Mockito.when(personRepository.findByName("ivan")).thenReturn(null);
		Assert.assertEquals(personService.findByName("ivan"), null);
	}

}
