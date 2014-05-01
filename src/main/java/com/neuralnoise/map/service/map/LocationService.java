package com.neuralnoise.map.service.map;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.map.util.IEntityService;

public interface LocationService extends IEntityService<Location> {

	Location create(double latitude, double longitude, String address);

}
