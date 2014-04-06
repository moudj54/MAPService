package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.map.EventDAO;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.service.map.util.AbstractEntityServiceImpl;

@Service
public class EventServiceImpl extends AbstractEntityServiceImpl<Event, EventDAO> implements EventService {

	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	
}
