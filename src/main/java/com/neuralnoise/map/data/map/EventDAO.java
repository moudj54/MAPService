package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Event;

@Repository
@Transactional
public class EventDAO extends AbstractContributedDAO<Event, Long> {

	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);
	
	public EventDAO() {
		super(Event.class);
	}
	
}
