package com.neuralnoise.map.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.PizzaDAO;
import com.neuralnoise.map.model.Pizza;

@Service
public class PizzaServiceImpl implements PizzaService {

	private static final Logger log = LoggerFactory.getLogger(PizzaServiceImpl.class);
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	public PizzaServiceImpl() { }

	@Override
	@Transactional(readOnly = true)
	public Pizza getById(Long id) throws DataAccessException {
		return pizzaDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Pizza create(Pizza pizza) {
		return pizzaDAO.create(pizza);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pizza> getAll() {
		return pizzaDAO.getAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Pizza pizza) {
		pizzaDAO.update(pizza);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		pizzaDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pizza> findByName(String name) {
		return pizzaDAO.findByName(name);
	}

}
