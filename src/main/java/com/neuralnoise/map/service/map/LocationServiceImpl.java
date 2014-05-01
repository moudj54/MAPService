package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.LocationDAO;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.map.util.AbstractEntityServiceImpl;

@Service
public class LocationServiceImpl extends AbstractEntityServiceImpl<Location, LocationDAO> implements LocationService {

	private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);
	
}
