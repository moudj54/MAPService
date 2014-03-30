package com.neuralnoise.map.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.Pizza;

@Repository
@Transactional
public class PizzaDAO {

	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public List<Pizza> findAll() {
		return em.createQuery("from Pizza").getResultList();
	}
}