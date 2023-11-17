package org.rampup.dao.impl;

import java.util.List;
import org.rampup.dao.JpaDaoImpl;
import org.rampup.dao.PersonAddressDao;
import org.rampup.model.PersonAddress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

/** * */
@Repository("personAddressRepository")
public class PersonAddressDaoImpl extends JpaDaoImpl implements PersonAddressDao {

	@Override
	@Transactional
	public PersonAddress addAddress(PersonAddress personAddress) throws PersistenceException {
		entityManager.persist(personAddress);
		return personAddress;
	}

	@Override
	public List<PersonAddress> findAlListPersonAddress() throws PersistenceException {
		Query query = entityManager.createQuery("SELECT pa FROM PersonAddress pa", PersonAddress.class);
		return query.getResultList();
	}

	@Override
	public PersonAddress findById(long id) throws PersistenceException{
		return entityManager.find(PersonAddress.class, id);
	}

	@Override
	@Transactional
	public PersonAddress updatePersonAddress(PersonAddress personAddress) throws PersistenceException {
		PersonAddress existingPersonAddress = entityManager.find(PersonAddress.class, personAddress.getId());
		
		if (existingPersonAddress != null) {
			existingPersonAddress.setCity(personAddress.getCity());
			existingPersonAddress.setCountry(personAddress.getCountry());
			existingPersonAddress.setStreet(personAddress.getStreet());
			existingPersonAddress.setZipCode(personAddress.getZipCode());
			
			return existingPersonAddress;
		} else {
            throw new EntityNotFoundException("Person Address not found for update");
		}
	}

	@Override
	@Transactional
	public void delete(long id) throws PersistenceException {
		PersonAddress existingPersonAddress = entityManager.find(PersonAddress.class, id);
		
		if (existingPersonAddress != null) {
			entityManager.remove(existingPersonAddress);
		} else {
            throw new EntityNotFoundException("Person Address not found for delete");
		}

	}
}