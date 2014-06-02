package com.neuralnoise.integration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.neuralnoise.integration.geo.Point;
import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CEvent;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.service.security.SecurityService;

@Component
public class AnswerHandler {

	private static final Logger log = LoggerFactory.getLogger(AnswerHandler.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private SecurityService securityService;

	@ServiceActivator
	public void handle(CAnswer answer) {
		log.info("Processing the answer for the request: " + answer.getRequest());
		log.info("Number of returned events: " + (answer.getEvents() != null ? answer.getEvents().size() : 0));

		for (CEvent ce : answer.getEvents()) {
			log.info("Event: " + ce);
		}
		
		// XXX - temporary
		//if (true)
		//	return;
		
		securityService.login("admin", "5f4dcc3b5aa765d61d8327deb882cf99");

		for (CEvent ce : answer.getEvents()) {
			String name = ce.getName();

			log.info("eventService: " + eventService);

			boolean found = false;
			List<Event> registeredEvents = eventService.findByName(name);

			for (Event registeredEvent : registeredEvents) {
				String rd = registeredEvent.getDescription(), prd = rd.substring(0, 32);
				String d = ce.getContent(), pd = d.substring(0, 32);
				if (prd.equals(pd)) {
					found = true;
				}
			}

			if (!found) {
				log.info("Adding event named: " + name);
				log.info("Event is: " + ce);

				Event event = new Event();

				event.setName(name);
				event.setDescription(ce.getContent());

				event.setContributor(securityService.getById("admin"));

				event.setStartDate(ce.getStartDate());
				event.setEndDate(ce.getEndDate());

				com.neuralnoise.integration.geo.Location cl = ce.getLocation();
				Location location = null;

				if (cl != null) {
					String lname = cl.getName();
					Point lpoint = cl.getPoint();

					location = new Location();
					location.setAddress(lname);
					location.setPoint(lpoint.getCoordinates().getLatitude(), lpoint.getCoordinates().getLongitude());
					event.setLocation(location);
				}

				eventService.create(event);
			}
		}
	}

}
