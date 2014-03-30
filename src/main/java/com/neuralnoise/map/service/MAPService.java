package com.neuralnoise.map.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.neuralnoise.map.model.domain.Event;

public interface MAPService {

	public Collection<Event> findEvents() throws DataAccessException;

}
