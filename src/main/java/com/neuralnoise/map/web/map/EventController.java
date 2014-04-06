package com.neuralnoise.map.web.map;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.service.map.util.IEntityService;

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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Event read(@PathVariable("id") Long id) {
		log.info("Reading event with id {}", id);
		Event event = eventService.getById(id);
		Validate.isTrue(event != null, "Unable to find event with id: " + id);
		return event;
	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Event> lookupByName(@PathVariable("name") String name) {
		log.info("Reading events with name {}", name);
		return eventService.findByName(name);
	}
	
	@RequestMapping(value = "/contributor/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Event> lookupByContributor(@PathVariable("name") String name) {
		log.info("Reading events with contributor {}", name);
		return eventService.findByContributor(name);
	}
	
}