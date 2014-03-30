package com.neuralnoise.map.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neuralnoise.map.data.PizzaDAO;
import com.neuralnoise.map.model.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

	@Autowired
	private PizzaDAO pizzaDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Pizza create(@RequestBody Pizza pizza) {
		log.info("Creating new pizza {}", pizza);
		return pizzaDAO.create(pizza);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Pizza> list() {
		log.info("Listing pizzas");
		return pizzaDAO.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Pizza read(@PathVariable("id") Long id) {
		log.info("Reading pizza with id {}", id);
		Pizza pizza = pizzaDAO.getById(id);
		Validate.isTrue(pizza != null, "Unable to find pizza with id: " + id);
		return pizza;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, @RequestBody Pizza pizza) {
		log.info("Updating pizza with id {} with {}", id, pizza);
		Validate.isTrue(id == pizza.getId(), "id doesn't match URL pizza's Id: " + pizza.getId());
		pizzaDAO.update(pizza);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		log.info("Deleting pizza with id {}", id);
		pizzaDAO.delete(id);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Pizza> lookupMemberById(@PathVariable("name") String name) {
		return pizzaDAO.findByName(name);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleClientErrors(Exception ex) {
		log.error(ex.getMessage(), ex);
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleServerErrors(Exception ex) {
		log.error(ex.getMessage(), ex);
		return ex.getMessage();
	}
}