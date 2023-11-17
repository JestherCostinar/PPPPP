package org.rampup.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rampup.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:repository-context-test.xml", })
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class PersonDaoTest {

	@Autowired
	private PersonDao personDao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("repository-context-test.xml");
		personDao = (PersonDao) context.getBean("personRepository");
	}

	@Test
	public void testFindPersonPositive() {
		// Positive Test Case
		long id = 1; // Provide a valid ID that exists in your test data
		Person person = personDao.findPerson(id);
		assertNotNull(person);
		assertEquals(id, person.getId());
	}

	@Test
	public void testFindPersonNegative() {
		// Negative Test Case
		long id = -1; // Provide an ID that does not exist in your test data
		Person person = personDao.findPerson(id);
		assertNull(person);
	}

	@Test
	public void testSearchForPersonsPositive() {
		// Positive Test Case
		// Assuming test data is set up in the database
		List<Person> persons = personDao.searchForPersons();
		assertNotNull(persons);
		assertFalse(persons.isEmpty());
	}

	@Test
	public void testSearchForPersonsNegative() {
		// Negative Test Case (Assuming there are no persons in the database)
		List<Person> persons = personDao.searchForPersons();
		assertNotNull(persons);
		assertTrue(persons.isEmpty());
	}

	@Test
	@DatabaseSetup(value = "classpath:com/accenture/aswhcm/person/dao/initialdatabase.xml")
	@ExpectedDatabase(value = "classpath:com/accenture/aswhcm/person/dao/aftercreate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testCreatePersonPositive() {
		// Positive Test Case
		Person person = new Person(); // Create a valid Person object for testing
		long id = personDao.createPerson(person);
		assertTrue(id > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreatePersonNegative() {
		// Negative Test Case (Assuming an exception is thrown for an invalid operation)
		Person invalidPerson = new Person(); // Create an invalid Person object for testing
		// Try to create the person, expecting a PersistenceException
		personDao.createPerson(invalidPerson);
	}

	@Test
	@DatabaseSetup(value = "classpath:com/accenture/aswhcm/person/dao/initialdatabase.xml")
	@ExpectedDatabase(value = "classpath:com/accenture/aswhcm/person/dao/afterupdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testUpdatePersonPositive() {
		// Positive Test Case
		long id = 1; // Provide a valid ID that exists in your test data
		Person updatedPerson = new Person(); // Create an updated Person object
		// Update the person and ensure no exceptions are thrown
		personDao.updatePerson(id, updatedPerson);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testUpdatePersonNegative() {
		// Negative Test Case (Updating a non-existing person)
		long nonExistingId = -1; // Provide an ID that does not exist in your test data
		Person updatedPerson = new Person(); // Create an updated Person object
		// Try to update the non-existing person, expecting an EntityNotFoundException
		personDao.updatePerson(nonExistingId, updatedPerson);
	}

	@Test
	@DatabaseSetup(value = "classpath:com/accenture/aswhcm/person/dao/initialdatabase.xml")
	@ExpectedDatabase(value = "classpath:com/accenture/aswhcm/person/dao/afterdelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void testDeletePersonPositive() {
		// Positive Test Case
		long id = 1; // Provide a valid ID that exists in your test data
		// Delete the person and ensure no exceptions are thrown
		personDao.deletePerson(id);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeletePersonNegative() {
		// Negative Test Case (Deleting a non-existing person)
		long nonExistingId = -1; // Provide an ID that does not exist in your test data
		// Try to delete the non-existing person, expecting an EntityNotFoundException
		personDao.deletePerson(nonExistingId);
	}

}
