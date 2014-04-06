package com.neuralnoise.map.data.map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Event;

@Repository
@Transactional
public class EventDAO extends AbstractContributedDAO<Event, Long> {

	public EventDAO() {
		super(Event.class);
	}
	
}
