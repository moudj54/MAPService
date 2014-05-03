package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.map.LocationService;
import com.neuralnoise.map.web.map.util.AbstractEntityController;

@Controller
@RequestMapping("/location")
public class LocationController extends AbstractEntityController<Location, LocationService> {

	private static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@RequestMapping(params = { "latitude", "longitude", "name" }, method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Location create(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude, @RequestParam(value = "name") String name) {
		Location location = service.create(latitude, longitude, name);
		return location;
	}

}
