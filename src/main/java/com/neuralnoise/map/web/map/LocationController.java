package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.map.LocationService;
import com.neuralnoise.map.web.map.util.AbstractEntityController;

@Controller
@RequestMapping("/location")
public class LocationController extends AbstractEntityController<Location, LocationService> {

	private static final Logger log = LoggerFactory.getLogger(LocationController.class);
	
}
