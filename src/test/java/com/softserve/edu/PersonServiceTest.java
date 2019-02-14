package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonService;

@TestExecutionListeners(MockitoTestExecutionListener.class)
@SpringBootTest
public class PersonServiceTest extends AbstractTestNGSpringContextTests {

	@MockBean
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Test
	public void testSomethingOnWidgetService() {
		// Precondition
		List<Person> expectedPersons = new ArrayList<>();
		Person testPerson = new Person();
		testPerson.setPid(0);
		testPerson.setName("SuperIvan");
		testPerson.setCity("SuperCity");
		expectedPersons.add(testPerson);
		//
		Mockito.when(personRepository.findByName("ivan")).thenReturn(expectedPersons);
		// Steps
		List<Person> actualPersons = personService.findByName("ivan");
		Assert.assertEquals(actualPersons.size(), expectedPersons.size());
		Assert.assertEquals(actualPersons.get(0).getName(),
				expectedPersons.get(0).getName());
		//
		//actualPersons = personService.findByName("petro");
		actualPersons = personService.findByName("ivan");
		System.out.println("actualPersons: " + actualPersons);
		//
		//Mockito.when(personRepository.findByName("ivan")).thenReturn(null);
		//Assert.assertEquals(personService.findByName("ivan"), null);
	}

}
