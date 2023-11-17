package org.rampup.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rampup.dao.PersonDao;
import org.rampup.exception.PersonNotFoundException;
import org.rampup.model.Person;

public class PersonServiceImplTest {

	@Mock
	private PersonDao personDao;

	@InjectMocks
	private PersonServiceImpl personService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindPerson_Positive() throws Exception {
		// Arrange
		long personId = 1;
		Person expectedPerson = new Person();
		expectedPerson.setId(1);
		expectedPerson.setFirstName("Test");
		expectedPerson.setLastName("Test");
		expectedPerson.setEmail("test@gmail.com");

		when(personDao.findPerson(personId)).thenReturn(expectedPerson);

		// Act
		Person result = personService.findPerson(personId);

		// Assert
		assertNotNull(result);
		assertEquals(expectedPerson, result);
	}

	@Test(expected = PersonNotFoundException.class)
	public void testFindPerson_Negative_PersonNotFoundException() throws Exception {
		// Arrange
		long personId = 1;
		when(personDao.findPerson(personId)).thenReturn(null);

		// Act
		personService.findPerson(personId);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindPerson_Negative_IllegalArgumentException() throws Exception {
		// Act
		personService.findPerson(-1L);
	}

	@Test
	public void testSearchForPersons_Positive() throws Exception {
		// Arrange
		List<Person> expectedPersons = new ArrayList<>();
		expectedPersons.add(new Person(1, "Uno", "Dos", "tres@gmail.com"));
		expectedPersons.add(new Person(2, "Kwatro", "Singko", "sais@gmail.com"));
		when(personDao.searchForPersons()).thenReturn(expectedPersons);

		// Act
		List<Person> result = personService.searchForPersons();

		// Assert
		assertNotNull(result);
		assertEquals(expectedPersons, result);
	}

	@Test
	public void testCreatePerson_Positive() throws Exception {
		// Arrange
		Person person = new Person("Uno", "Dos", "tres@gmail.com");
		when(personDao.createPerson(person)).thenReturn((long) 1);

		// Act
		long createdPersonId = personService.createPerson(person);

		// Assert
		assertEquals(1, createdPersonId);
	}

	@Test(expected = NullPointerException.class)
	public void testCreatePerson_Negative_IllegalArgumentException() throws Exception {
		// Act
		personService.createPerson(null);
	}

	@Test
	public void testUpdatePerson_Positive() throws Exception {
		// Arrange
		long personId = 1;
		Person updatedPerson = new Person(personId, "Updated FirstName", "Updated Lastname", "Updated Email");
		when(personDao.findPerson(personId)).thenReturn(new Person(personId, "FirstName", "Lastname", "Email"));

		// Act
		personService.updatePerson(personId, updatedPerson);

		// Assert - Verify that updatePerson method was called with the correct
		// arguments
		verify(personDao).updatePerson(eq(personId), eq(updatedPerson));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdatePerson_Negative_IllegalArgumentException() throws Exception {
		// Act
		personService.updatePerson(-1L, new Person());
	}

	@Test(expected = PersonNotFoundException.class)
	public void testUpdatePerson_Negative_PersonNotFoundException() throws Exception {
		// Arrange
		long personId = 1L;
		when(personDao.findPerson(personId)).thenReturn(null);

		// Act
		personService.updatePerson(personId, new Person());
	}

	@Test
	public void testDeletePerson_Positive() throws Exception {
		// Arrange
		long personId = 1;
		when(personDao.findPerson(personId)).thenReturn(new Person(personId, "Uno", "Dos", "tres@gmail.com"));

		// Act
		personService.deletePerson(personId);

		// Assert - Verify that deletePerson method was called with the correct argument
		verify(personDao).deletePerson(eq(personId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeletePerson_Negative_IllegalArgumentException() throws Exception {
		// Act
		personService.deletePerson(-1L);
	}

	@Test(expected = PersonNotFoundException.class)
	public void testDeletePerson_Negative_PersonNotFoundException() throws Exception {
		// Arrange
		long personId = 1L;
		when(personDao.findPerson(personId)).thenReturn(null);

		// Act
		personService.deletePerson(personId);
	}

}
