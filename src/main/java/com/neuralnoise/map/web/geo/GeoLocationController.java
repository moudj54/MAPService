package com.neuralnoise.map.web.geo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.web.util.Feature;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

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

	@RequestMapping(value = "/g/google/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Feature> lookupByNameGeoJson(@PathVariable("name") String name) throws IOException {
		List<Location> locations = geoLocationService.lookupGoogle(name);
		List<Feature> features = Lists.newLinkedList();
		for (Location location : locations) {
			features.add(new Feature(location.getProperties(), location.getPoint()));
		}
		
		return features;
	}
	
	@RequestMapping(value = "/GeoJSON", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Feature geoJSON() throws IOException {

		Coordinate[] coordArr = { new Coordinate(51.594f, 4.777f) };
		CoordinateSequence cs = new CoordinateArraySequence(coordArr);

		GeometryFactory factory = new GeometryFactory();

		Map<String, String> map = Maps.newHashMap();
		map.put("x", "y");
		
		Point point = new Point(cs, factory);
		return new Feature(map, point);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	com.vividsolutions.jts.geom.Point test() throws IOException {
		Coordinate[] coordArr = { new Coordinate(51.594f, 4.777f) };
		CoordinateSequence cs = new CoordinateArraySequence(coordArr);

		GeometryFactory factory = new GeometryFactory();

		com.vividsolutions.jts.geom.Point point = new com.vividsolutions.jts.geom.Point(cs, factory);
		return point;
	}
	
}
