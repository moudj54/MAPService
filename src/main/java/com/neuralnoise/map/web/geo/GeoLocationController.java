package com.neuralnoise.map.web.geo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;

@Controller
@RequestMapping("/geoLocation")
public class GeoLocationController {

	@Autowired
	private GeoLocationService geoLocationService;

	@RequestMapping(value = "/nominatim/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Location> lookupByNameN(@PathVariable("name") String name) throws IOException {
		List<Location> locations = geoLocationService.lookupNominatim(name);
		return locations;
	}

	@RequestMapping(value = "/google/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Location> lookupByNameG(@PathVariable("name") String name) throws IOException {
		List<Location> locations = geoLocationService.lookupGoogle(name);
		return locations;
	}

}
