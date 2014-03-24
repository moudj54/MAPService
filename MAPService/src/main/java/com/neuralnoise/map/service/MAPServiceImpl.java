package com.neuralnoise.map.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.model.domain.Event;

@Service
public class MAPServiceImpl implements MAPService {

	private EventRepository eventRepository;
	
	public MAPServiceImpl() { }

	@Override
    @Transactional(readOnly = true)
	public Collection<Event> findEvents() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
