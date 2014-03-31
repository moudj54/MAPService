package com.neuralnoise.map.service;

import java.io.IOException;
import java.util.List;

import com.neuralnoise.map.model.geo.GeographicCoordinates;

public interface GeoLocationService {

	public List<GeographicCoordinates> lookupNominatim(String address) throws IOException;

	public List<GeographicCoordinates> lookupGoogle(String address) throws IOException;
	
}
