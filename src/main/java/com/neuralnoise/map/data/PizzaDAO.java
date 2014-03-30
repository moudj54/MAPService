package com.neuralnoise.map.data;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.Pizza;

@Repository
@Transactional
public class PizzaDAO extends AbstractDAO<Pizza> {

	public PizzaDAO() {
		super(Pizza.class);
	}
	
}