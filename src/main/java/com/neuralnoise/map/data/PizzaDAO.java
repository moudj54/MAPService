package com.neuralnoise.map.data;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;
import com.neuralnoise.map.model.Pizza;

@Repository
@Transactional
public class PizzaDAO extends AbstractDAO<Pizza> {

	public PizzaDAO() {
		super(Pizza.class);
	}

	public List<Pizza> findByName(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " p where p.name = :name");
		return query.setParameter("name", name).getResultList();
	}

	public void delete(Long id) {
		Pizza pizza = getById(id);
		Preconditions.checkState(pizza != null);
		delete(pizza);
	}
	
}