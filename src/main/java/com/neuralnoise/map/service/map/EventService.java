package com.neuralnoise.map.service.map;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neuralnoise.map.model.contributed.Event;

@Service
public interface EventService {

	public Event create(Event event);
	
	public List<Event> getAll();
	
}
