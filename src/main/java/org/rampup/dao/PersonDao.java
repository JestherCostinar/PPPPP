package org.rampup.dao;

import java.util.List;
import org.rampup.model.Person;
import jakarta.persistence.PersistenceException;

/** * TODO: Description.. */
public interface PersonDao {
	/**
	 * * Description: Gets either Person with ID or null (use named query) *
	 * * @param id * @return Person Object with the id of @param id * @throws
	 * PersistenceException
	 */
	Person findPerson(long id) throws PersistenceException;

	/**
	 * * Description: Gets all existing persons (use named query) * * @return list
	 * of Persons Object * @throws PersistenceException
	 */
	List<Person> searchForPersons() throws PersistenceException;

	/**
	 * * @param person * @return Person Object created Id * @throws
	 * PersistenceException
	 */
	long createPerson(Person person) throws PersistenceException;

	/** * @param person * @throws PersistenceException */
	void updatePerson(long id, Person person) throws PersistenceException;

	/** * @param id * @throws PersistenceException */
	void deletePerson(long id) throws PersistenceException;
}