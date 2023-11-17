package org.rampup.dao.impl;

import java.util.List;
import org.rampup.dao.JpaDaoImpl;
import org.rampup.dao.PersonPassportDao;
import org.rampup.model.PersonPassport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

/** * */
@Repository("personPassportRepository")
public class PersonPassportDaoImpl extends JpaDaoImpl implements PersonPassportDao {

	@Override
	@Transactional
	public PersonPassport addPassport(PersonPassport personPassport) throws PersistenceException {
		entityManager.persist(personPassport);
		return personPassport;
	}

	@Override
	public List<PersonPassport> findAllPersonPassport() throws PersistenceException {
		Query query = entityManager.createQuery("SELECT pp FROM PersonPassport pp", PersonPassport.class);
		return query.getResultList();
	}

	@Override
	public PersonPassport findById(long id) throws PersistenceException {
		return entityManager.find(PersonPassport.class, id);
	}

	@Override
	@Transactional
	public PersonPassport updatePersonAddress(PersonPassport personPassport) throws PersistenceException {
		PersonPassport existingPersonPassport = entityManager.find(PersonPassport.class, personPassport.getId());

		if (existingPersonPassport != null) {
			existingPersonPassport.setPassportNumber(personPassport.getPassportNumber());
			existingPersonPassport.setPerson(personPassport.getPerson());

			return existingPersonPassport;
		} else {
			throw new EntityNotFoundException("Person Address not found for update");
		}
	}

	@Override
	@Transactional
	public void delete(long id) throws PersistenceException {
		PersonPassport existingPersonPassport = entityManager.find(PersonPassport.class, id);

		if (existingPersonPassport != null) {
			entityManager.remove(existingPersonPassport);
		} else {
			throw new EntityNotFoundException("Person Passport not found for delete");
		}
	}

}
