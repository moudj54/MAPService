package com.neuralnoise.map.web;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neuralnoise.map.model.Pizza;
import com.neuralnoise.map.service.PizzaService;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

	private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Pizza create(@ModelAttribute Pizza pizza) {
		log.info("Creating new pizza {}", pizza);
		return pizzaService.create(pizza);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Pizza> list() {
		log.info("Listing pizzas");
		return pizzaService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Pizza read(@PathVariable("id") Long id) {
		log.info("Reading pizza with id {}", id);
		Pizza pizza = pizzaService.getById(id);
		Validate.isTrue(pizza != null, "Unable to find pizza with id: " + id);
		return pizza;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, @RequestBody Pizza pizza) {
		log.info("Updating pizza with id {} with {}", id, pizza);
		Validate.isTrue(id == pizza.getId(), "id doesn't match URL pizza's Id: " + pizza.getId());
		pizzaService.update(pizza);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		log.info("Deleting pizza with id {}", id);
		pizzaService.deleteById(id);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Pizza> lookupByName(@PathVariable("name") String name) {
		return pizzaService.findByName(name);
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