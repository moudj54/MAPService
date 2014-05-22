package com.neuralnoise.map.web.map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.web.util.Feature;

@Controller
@RequestMapping("/entity")
public class EntityController {

	private static final Logger log = LoggerFactory.getLogger(EntityController.class);
	
	@Autowired
	private ArtisanController artisanController;
	
	@Autowired
	private EventController eventController;
	
	@Autowired
	private MuseumController museumController;
	
	@Autowired
	private OrganizationController organizationController;
	
	private static boolean isParameter(HttpServletRequest request, String name) {
		final String parameter = request.getParameter(name);
		return (parameter != null) && Boolean.parseBoolean(parameter);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<AbstractContributedEntity> get(HttpServletRequest request) {
		
		log.info("Request: " + request.getParameterMap());
		
		boolean isArtisans = isParameter(request, "artisans");
		boolean isEvents = isParameter(request, "events");
		boolean isMuseums = isParameter(request, "museums");
		boolean isOrganizations = isParameter(request, "organizations");
		
		log.info("Artisans: {}, Events: {}, Museums: {}, Organizations: {}",
				isArtisans, isEvents, isMuseums, isOrganizations);

		List<AbstractContributedEntity> entities = Lists.newLinkedList();

		if (isArtisans) {
			entities.addAll(artisanController.list());
		}
		
		if (isEvents) {
			entities.addAll(eventController.list());
		}
		
		if (isMuseums) {
			entities.addAll(museumController.list());
		}
		
		if (isOrganizations) {
			entities.addAll(organizationController.list());
		}
		
		return entities;
	}
	
	@RequestMapping(value = "/feature", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Feature> features(HttpServletRequest request) {
		List<AbstractContributedEntity> entities = get(request);
		List<Feature> features = Lists.newLinkedList();
		for (AbstractContributedEntity entity : entities) {
			Feature feature = new Feature(entity.getProperties(), entity.getLocation().getPoint());
			features.add(feature);
		}
		return features;
	}
}
