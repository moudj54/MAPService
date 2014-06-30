package com.neuralnoise.integration;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.neuralnoise.integration.geo.Point;
import com.neuralnoise.integration.util.CAnswer;
import com.neuralnoise.integration.util.CEvent;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.Annotation;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.ContributedEntity;
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

	private IContributedEntityService<? extends ContributedEntity> getService(ContributedEntity entity) {
		IContributedEntityService<? extends ContributedEntity> service = null;
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
			if (ce != null && ce.getType() != null) {

				ContributedEntity entity = null;

				switch (ce.getType()) {
				case "artisan": {
					entity = new Artisan();
				}
					break;
				case "organization": {
					entity = new Organization();
				}
					break;
				case "museum": {
					entity = new Museum();
				}
					break;
				case "event": {
					entity = new Event();
				}
				}

				if (entity != null) {

					IContributedEntityService<? extends ContributedEntity> service = getService(entity);

					String name = ce.getName();

					boolean found = false;
					List<? extends ContributedEntity> registeredEntities = service.findByName(name);

					ContributedEntity foundEntity = null;

					for (ContributedEntity registeredEntity : registeredEntities) {
						String rd = registeredEntity.getDescription(), prd = (rd == null ? "" : rd.substring(0, Math.min(rd.length(), 32)));
						String d = ce.getContent(), pd = (d == null ? "" : d.substring(0, Math.min(d.length(), 32)));
						if (prd.equals(pd)) {
							foundEntity = registeredEntity;
							found = true;
						}
					}

					log.info("Handling event named: " + name);
					log.info("Event is: " + ce);

					entity.setName(name);
					entity.setDescription(ce.getContent());

					entity.setContributor(securityService.getById("admin"));
					
					Set<Annotation> annotations = Sets.newHashSet();
					
					if (ce.getAnnotations() != null) {
						for (String annotation : ce.getAnnotations()) {
							Annotation ann = new Annotation();
							ann.setId(annotation);
							annotations.add(ann);
						}
					}
					
					entity.setAnnotations(annotations);
					
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

					if (found) {
						if (service instanceof ArtisanService) {
							ArtisanService s = (ArtisanService) service;
							s.deleteById(foundEntity.getId());
						} else if (service instanceof OrganizationService) {
							OrganizationService s = (OrganizationService) service;
							s.deleteById(foundEntity.getId());
						} else if (service instanceof MuseumService) {
							MuseumService s = (MuseumService) service;
							s.deleteById(foundEntity.getId());
						} else if (service instanceof EventService) {
							EventService s = (EventService) service;
							s.deleteById(foundEntity.getId());
						}
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

}
