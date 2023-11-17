package org.rampup.dao.impl;

import java.util.List;
import org.rampup.dao.JpaDaoImpl;
import org.rampup.dao.PersonDao;
import org.rampup.model.Person;
import org.rampup.model.PersonAddress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

/** * */
@Repository("personRepository")
public class PersonDaoImpl extends JpaDaoImpl implements PersonDao {

	@Override
	public Person findPerson(long id) throws PersistenceException {
		Query query = entityManager.createNamedQuery("FindPerson");
		query.setParameter("id", id);
		
		try {
			return (Person) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Person> searchForPersons() throws PersistenceException {
		Query query = entityManager.createNamedQuery("SearchForPersons");
		return query.getResultList();
	}

	@Override
	@Transactional
	public long createPerson(Person person) throws PersistenceException {
		for (PersonAddress address : person.getAddresses()) {
			entityManager.persist(address);
		}
		entityManager.persist(person);
		return person.getId();
	}

	@Override
	@Transactional
	public void updatePerson(long id, Person person) throws PersistenceException {
		Person existingPerson = findPerson(id);
		
		if (existingPerson != null) {
			existingPerson.setFirstName(person.getFirstName());
			existingPerson.setLastName(person.getLastName());
			existingPerson.setEmail(person.getEmail());
		} else {
            throw new EntityNotFoundException("Person not found for update");
		}
		
	}

	@Override
	@Transactional
	public void deletePerson(long id) throws PersistenceException {
		Person existingPerson = findPerson(id);
		if (existingPerson != null) {
			entityManager.remove(existingPerson);
		} else {
            throw new EntityNotFoundException("Person not found for delete");
		}
	}

}
