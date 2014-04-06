package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

public abstract class AbstractEntityServiceImpl<T extends AbstractContributedEntity, D extends AbstractContributedDAO<T, Long>> implements IEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractEntityServiceImpl.class);
	
	@Autowired
	private D eventDAO;
	
	@Autowired
	private SecurityService securityService;
	
	@Override
	@Transactional(readOnly = false)
	public T create(T event) {
		UserEntity ue = securityService.current();
		event.setContributor(ue);
		return eventDAO.create(event);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return eventDAO.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> findByName(String name) {
		return eventDAO.findByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> findByContributor(String name) {
		return eventDAO.findByContributor(name);
	}

	@Override
	public T getById(Long id) {
		return eventDAO.getById(id);
	}

}
