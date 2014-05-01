package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.AddressDAO;
import com.neuralnoise.map.data.LocationDAO;
import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

public abstract class AbstractEntityServiceImpl<T extends AbstractContributedEntity, D extends AbstractContributedDAO<T, Long>> implements IEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractEntityServiceImpl.class);
	
	@Autowired
	private D entityDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired
	private SecurityService securityService;
	
	@Override
	@Transactional(readOnly = false)
	public T create(T event) {
		boolean authorized = false;
		
		UserEntity ue = securityService.current();

		if (ue != null) {
			authorized = true;
		}
		
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		
		event.setContributor(ue);
		return entityDAO.create(event);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return entityDAO.getAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		boolean authorized = false;
		
		UserEntity ue = securityService.current();
		
		if (ue != null) {
			if (ue.isAdmin()) {
				authorized = true;
			} else {
				T e = entityDAO.getById(id);
				if (e != null) {
					if (e.getContributor().getName().equals(ue.getName())) {
						authorized = true;
					}
				}
			}
		}
		
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		
		entityDAO.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> findByName(String name) {
		return entityDAO.findByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> findByContributor(String name) {
		return entityDAO.findByContributor(name);
	}

	@Override
	public T getById(Long id) {
		return entityDAO.getById(id);
	}

}
