package com.neuralnoise.map.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.neuralnoise.map.model.domain.Event;
import com.neuralnoise.map.service.MAPService;

@Controller
@SessionAttributes(types = Event.class)
public class EventController {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);
	
	private final MAPService mapService;

	@Autowired
	public EventController(MAPService mapService) {
		this.mapService = mapService;
	}
	
	@RequestMapping("/event")
	public @ResponseBody Event person(@RequestParam(value = "name", required = false, defaultValue = "Domenico") String name) {
		log.info("Returning a Event object.. ");
		return new Event();
	}

	
}
