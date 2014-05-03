package com.neuralnoise.map.web.map.util;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neuralnoise.map.model.AbstractBaseEntity;
import com.neuralnoise.map.service.map.util.IEntityService;

public class AbstractEntityController<T extends AbstractBaseEntity, S extends IEntityService<T>> {

	private static final Logger log = LoggerFactory.getLogger(AbstractEntityController.class);

	@Autowired
	protected S service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public T create(@ModelAttribute T entity) {
		log.info("Creating new entity {}", entity);
		T ret = service.create(entity);
		log.info("Created entity: " + ret);
		return ret;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<T> list() {
		log.info("Listing entities");
		return service.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	T read(@PathVariable("id") Long id) {
		log.info("Reading entity with id {}", id);
		T entity = service.getById(id);
		Validate.isTrue(entity != null, "Unable to find entity with id: " + id);
		return entity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		log.info("Deleting entity with id {}", id);
		service.deleteById(id);
	}

}