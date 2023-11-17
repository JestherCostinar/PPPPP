package org.rampup.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

/** * */
public abstract class JpaDaoImpl {
	
//	@PersistenceUnit(unitName = "Person")
//	protected EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	protected EntityManager entityManager;

//	protected void creatEntityManager() {
//		this.entityManager = entityManagerFactory.createEntityManager();
//	}
}