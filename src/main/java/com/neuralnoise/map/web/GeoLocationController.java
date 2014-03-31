package com.neuralnoise.map.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.geo.GeographicCoordinates;
import com.neuralnoise.map.service.GeoLocationService;

@Controller
@RequestMapping("/geoLocation")
public class GeoLocationController {

	@Autowired
	private GeoLocationService geoLocationService;
	
	@RequestMapping(value = "/nominatim/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<GeographicCoordinates> lookupByNameN(@PathVariable("name") String name) throws IOException {
		return geoLocationService.lookupNominatim(name);
	}

	@RequestMapping(value = "/google/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<GeographicCoordinates> lookupByNameG(@PathVariable("name") String name) throws IOException {
		return geoLocationService.lookupGoogle(name);
	}
	
}
