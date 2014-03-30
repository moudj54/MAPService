package com.neuralnoise.map.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.data.PizzaDAO;
import com.neuralnoise.map.model.Pizza;

@Controller
public class PizzaRESTController {

	@Autowired
	private PizzaDAO pizzaDAO;

	@RequestMapping("/pizza")
	public @ResponseBody
	List<Pizza> list(Model model) {
		List<Pizza> pizzas = pizzaDAO.findAll();
		model.addAttribute("pizzas", pizzas);
		return pizzas;
	}
}