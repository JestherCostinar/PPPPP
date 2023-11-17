package org.rampup.dao;

import java.util.List;
import org.rampup.model.PersonAddress;

import jakarta.persistence.PersistenceException;

/** * */
public interface PersonAddressDao {
	PersonAddress addAddress(PersonAddress personAddress) throws PersistenceException;

	List<PersonAddress> findAlListPersonAddress() throws PersistenceException;

	PersonAddress findById(long id) throws PersistenceException;

	PersonAddress updatePersonAddress(PersonAddress personAddress) throws PersistenceException;

	void delete(long id) throws PersistenceException;
}
