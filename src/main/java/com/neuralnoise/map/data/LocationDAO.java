package com.neuralnoise.map.data;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.geo.Location;

@Repository
@Transactional
public class LocationDAO extends AbstractDAO<Location, Long> {

	private static final Logger log = LoggerFactory.getLogger(LocationDAO.class);

	public LocationDAO() {
		super(Location.class);
	}

	public Location create(double latitude, double longitude, String address) {
		Location entity = new Location(latitude, longitude, address);
		return create(entity);
	}

}
