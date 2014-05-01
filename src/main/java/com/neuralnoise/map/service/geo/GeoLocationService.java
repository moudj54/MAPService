package com.neuralnoise.map.service.geo;

import java.io.IOException;
import java.util.List;

import com.neuralnoise.map.model.geo.Location;

public interface GeoLocationService {

	public List<Location> lookupNominatim(String address) throws IOException;

	public List<Location> lookupGoogle(String address) throws IOException;
	
}
