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
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.model.map.Museum;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.service.map.ArtisanService;
import com.neuralnoise.map.service.map.EventService;
import com.neuralnoise.map.service.map.MuseumService;
import com.neuralnoise.map.service.map.OrganizationService;
import com.neuralnoise.map.service.map.util.IContributedEntityService;
import com.neuralnoise.map.service.security.SecurityService;

@Component
public class AnswerHandler {

	private static final Logger log = LoggerFactory.getLogger(AnswerHandler.class);
	
	@Autowired
	private ArtisanService artisanService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private MuseumService museumService;
	@Autowired
	private EventService eventService;

	@Autowired
	private SecurityService securityService;

	private IContributedEntityService<? extends AbstractContributedEntity> getService(AbstractContributedEntity entity) {
		IContributedEntityService<? extends AbstractContributedEntity> service = null;
		if (entity != null) {
			if (entity instanceof Artisan) {
				service = artisanService;
			} else if (entity instanceof Organization) {
				service = organizationService;
			} else if (entity instanceof Museum) {
				service = museumService;
			} else if (entity instanceof Event) {
				service = eventService;
			}
		}
		return service;
	}
	
	@ServiceActivator
	public void handle(CAnswer answer) {
		log.info("Processing the answer for the request: " + answer.getRequest());
		log.info("Number of returned events: " + (answer.getEvents() != null ? answer.getEvents().size() : 0));

		for (CEvent ce : answer.getEvents()) {
			log.info("Event: " + ce);
		}
		
		securityService.login("admin", "5f4dcc3b5aa765d61d8327deb882cf99");

		for (CEvent ce : answer.getEvents()) {
			
			AbstractContributedEntity entity = null;
			switch (ce.getType()) {
			case "artisan": {
				entity = new Artisan();
			} break;
			case "organization": {
				entity = new Organization();
			} break;
			case "museum": {
				entity = new Museum();
			} break;
			case "event": {
				entity = new Event();
			}
			}
			
			IContributedEntityService<? extends AbstractContributedEntity> service = getService(entity);
			
			String name = ce.getName();

			boolean found = false;
			List<? extends AbstractContributedEntity> registeredEvents = service.findByName(name);

			for (AbstractContributedEntity registeredEvent : registeredEvents) {
				String rd = registeredEvent.getDescription(), prd = (rd == null ? "" : rd.substring(0, 32));
				String d = ce.getContent(), pd = (d == null ? "" : d.substring(0, 32));
				if (prd.equals(pd)) {
					found = true;
				}
			}

			if (!found) {
				log.info("Adding event named: " + name);
				log.info("Event is: " + ce);

				entity.setName(name);
				entity.setDescription(ce.getContent());

				entity.setContributor(securityService.getById("admin"));

				if (entity instanceof Event) {
					Event event = (Event) entity;
					event.setStartDate(ce.getStartDate());
					event.setEndDate(ce.getEndDate());
				}

				com.neuralnoise.integration.geo.Location cl = ce.getLocation();
				Location location = null;

				if (cl != null) {
					String lname = cl.getName();
					Point lpoint = cl.getPoint();

					location = new Location();
					location.setAddress(lname);
					location.setPoint(lpoint.getCoordinates().getLatitude(), lpoint.getCoordinates().getLongitude());
					entity.setLocation(location);
				}

				if (service instanceof ArtisanService) {
					((ArtisanService) service).create((Artisan) entity);
				} else if (service instanceof OrganizationService) {
					((OrganizationService) service).create((Organization) entity);
				} else if (service instanceof MuseumService) {
					((MuseumService) service).create((Museum) entity);
				} else if (service instanceof EventService) {
					((EventService) service).create((Event) entity);
				}
			}
		}
	}

}
