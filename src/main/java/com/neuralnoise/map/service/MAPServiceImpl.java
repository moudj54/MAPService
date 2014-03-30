package com.neuralnoise.map.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.model.domain.Event;

@Service
public class MAPServiceImpl implements MAPService {

	public MAPServiceImpl() {
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Event> findEvents() throws DataAccessException {

		return null;
	}

}
