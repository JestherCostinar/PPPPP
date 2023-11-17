package org.rampup.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.rampup.dao.PersonDao;
import org.rampup.exception.PersonNotFoundException;
import org.rampup.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
public class PersonServiceImpl implements PersonService{
	
//	@Autowired
	private PersonDao personDao;
	
	public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

	@Override
	@Transactional
	public Person findPerson(long id) throws IllegalArgumentException, PersonNotFoundException, Exception {
		Validate.isTrue(id > 0, "Person ID not valid");
		
		Person person = personDao.findPerson(id);
		if (person == null) {
			throw new PersonNotFoundException("Person not found with ID: " + id);
		}
		
		return person;
	}

	@Override
	public List<Person> searchForPersons() throws Exception {
		return personDao.searchForPersons();
	}

	@Override
	public long createPerson(Person person) throws IllegalArgumentException, Exception {
		Validate.notNull(person, "Person cannot be null");
        Validate.isTrue(person.getId() == 0, "Person ID not valid");

        long createdPerson = personDao.createPerson(person);
        return createdPerson;
	}

	@Override
	public void updatePerson(long id, Person person) throws IllegalArgumentException, Exception {
		Validate.notNull(person, "Person cannot be null");
        Validate.isTrue(id > 0, "Person ID not valid");
        
        Person existingPerson = personDao.findPerson(id);
        if (existingPerson == null) {
			throw new PersonNotFoundException("Person not found with ID: " + id);
        }
		
        // UPDATE
        personDao.updatePerson(id, person);
	}

	@Override
	public void deletePerson(long id) throws IllegalArgumentException, PersonNotFoundException, Exception {
		Validate.isTrue(id > 0, "Person ID not valid");
		
		Person existingPerson = personDao.findPerson(id);
		if (existingPerson == null) {
			throw new PersonNotFoundException("Person not found with ID: " + id);
        }
		
		// DELETE
		personDao.deletePerson(id);
	}

}
