package com.neuralnoise.map.service.map;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.map.util.INamedEntityService;

public interface LocationService extends INamedEntityService<Location> {

	Location create(double latitude, double longitude, String address);

}
