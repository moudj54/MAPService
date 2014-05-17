package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.LocationDAO;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.map.util.AbstractNamedEntityServiceImpl;

@Service
public class LocationServiceImpl extends AbstractNamedEntityServiceImpl<Location, LocationDAO> implements LocationService {

	private static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

	@Override
	@Transactional(readOnly = false)
	public Location create(double latitude, double longitude, String address) {
		boolean authorized = false;

		UserEntity ue = securityService.current();

		if (ue != null) {
			authorized = true;
		}

		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}

		return entityDAO.create(latitude, longitude, address);
	}

}
