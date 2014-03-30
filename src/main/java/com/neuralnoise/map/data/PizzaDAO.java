package com.neuralnoise.map.data;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.Pizza;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PizzaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Pizza> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List pizzas = session.createQuery("from Pizza").list();
		return pizzas;
	}
}