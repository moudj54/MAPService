package com.neuralnoise.map.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.data.PizzaDAO;
import com.neuralnoise.map.model.Pizza;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaDAO pizzaDAO;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Pizza> list() {
		return pizzaDAO.getAll();
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Pizza lookupMemberById(@PathVariable("id") Long id) {
        return pizzaDAO.getById(id);
    }
    
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Pizza> lookupMemberById(@PathVariable("name") String name) {
        return pizzaDAO.findByName(name);
    }
}