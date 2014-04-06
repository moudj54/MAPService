package com.neuralnoise.map.service.map;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.EventDAO;
import com.neuralnoise.map.model.Pizza;
import com.neuralnoise.map.model.contributed.Event;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

@Service
public class EventServiceImpl implements EventService {

	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private SecurityService securityService;
	
	@Override
	@Transactional(readOnly = false)
	public Event create(Event event) {
		UserEntity ue = securityService.current();
		event.setContributor(ue);
		return eventDAO.create(event);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Event> getAll() {
		return eventDAO.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Event> findByName(String name) {
		return eventDAO.findByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Event> findByContributor(String name) {
		return eventDAO.findByContributor(name);
	}

	@Override
	public Event getById(Long id) {
		return eventDAO.getById(id);
	}

}