package com.neuralnoise.map.service;

import java.util.List;

import com.neuralnoise.map.model.Pizza;

public interface PizzaService {

	public Pizza getById(Long id);

	public Pizza create(Pizza pizza);

	public List<Pizza> getAll();

	public void update(Pizza pizza);

	public void deleteById(Long id);

	public List<Pizza> findByName(String name);

}
