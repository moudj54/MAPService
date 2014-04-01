package com.neuralnoise.map.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.geo.GeoLocationUtils;
import com.neuralnoise.map.model.geo.GeographicCoordinates;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

	private static final Logger log = LoggerFactory.getLogger(GeoLocationServiceImpl.class);

	public static final String LANGUAGE_DEFAULT = "en";

	public GeoLocationServiceImpl() { }

	@Override
	public List<GeographicCoordinates> lookupNominatim(String address) throws IOException {
		return GeoLocationUtils.queryNominatim(address, LANGUAGE_DEFAULT);
	}

	@Override
	public List<GeographicCoordinates> lookupGoogle(String address) throws IOException {
		return GeoLocationUtils.queryGoogle(address, LANGUAGE_DEFAULT);
	}

}