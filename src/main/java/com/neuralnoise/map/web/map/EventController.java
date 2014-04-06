package com.neuralnoise.map.web.map;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.Pizza;
import com.neuralnoise.map.model.contributed.Event;
import com.neuralnoise.map.service.map.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Event create(@ModelAttribute Event event) {
		log.info("Creating new event {}", event);
		return eventService.create(event);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Event> list() {
		log.info("Listing events");
		return eventService.getAll();
	}
	
}