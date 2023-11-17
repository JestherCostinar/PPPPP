package org.rampup.dao;

import java.util.List;
import org.rampup.model.PersonPassport;

import jakarta.persistence.PersistenceException;

/** * */
public interface PersonPassportDao {
	PersonPassport addPassport(PersonPassport personPassport) throws PersistenceException;

	List<PersonPassport> findAllPersonPassport() throws PersistenceException;

	PersonPassport findById(long id) throws PersistenceException;

	PersonPassport updatePersonAddress(PersonPassport personPassport) throws PersistenceException;

	void delete(long id) throws PersistenceException;
}
