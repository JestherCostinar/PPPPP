package org.rampup.service;

import java.util.List;

import org.rampup.exception.PersonNotFoundException;
import org.rampup.model.Person;
import org.springframework.stereotype.Service;

public interface PersonService {
	Person findPerson(long id) throws IllegalArgumentException, PersonNotFoundException, Exception;
	List<Person> searchForPersons() throws Exception;
	long createPerson(Person person) throws IllegalArgumentException, Exception;
	void updatePerson(long id, Person person) throws IllegalArgumentException, Exception;
	void deletePerson(long id) throws IllegalArgumentException, PersonNotFoundException, Exception;
}
