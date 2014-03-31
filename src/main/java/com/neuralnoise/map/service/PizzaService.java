package com.neuralnoise.map.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.neuralnoise.map.model.Pizza;

public interface PizzaService {

	Pizza getById(Long id) throws DataAccessException;

	Pizza create(Pizza pizza);

	List<Pizza> getAll();

	void update(Pizza pizza);

	void deleteById(Long id);

	List<Pizza> findByName(String name);

}
