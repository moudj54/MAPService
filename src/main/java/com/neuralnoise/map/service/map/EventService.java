package com.neuralnoise.map.service.map;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.model.map.Event;

@Service
public interface EventService {

	public Event getById(Long id) throws DataAccessException;
	
	public Event create(Event event);
	
	public List<Event> getAll();
	
	public List<Event> findByName(String name);
	
	public List<Event> findByContributor(String name);
	
}
