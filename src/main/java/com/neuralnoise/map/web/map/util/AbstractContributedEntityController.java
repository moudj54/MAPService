package com.neuralnoise.map.web.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.map.ContributedEntity;
import com.neuralnoise.map.service.map.util.IContributedEntityService;

public class AbstractContributedEntityController<T extends ContributedEntity, S extends IContributedEntityService<T>> extends AbstractEntityController<T, S> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedEntityController.class);

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<T> lookupByName(@PathVariable("name") String name) {
		log.info("Reading entities with name {}", name);
		return service.findByName(name);
	}

	@RequestMapping(value = "/contributor/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<T> lookupByContributor(@PathVariable("name") String name) {
		log.info("Reading entities with contributor {}", name);
		return service.findByContributor(name);
	}

}