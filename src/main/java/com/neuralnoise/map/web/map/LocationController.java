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

	public class JSONLocation {

		private final Long id;
		private final String geometry, name;

		public JSONLocation(Long id, String geometry, String name) {
			this.id = id;
			this.geometry = geometry;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public String getGeometry() {
			return geometry;
		}

		public String getName() {
			return name;
		}

	}

	@RequestMapping(params = { "latitude", "longitude", "address" }, method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public JSONLocation create(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude, @RequestParam(value = "address") String address) {
		Location location = service.create(latitude, longitude, address);
		return new JSONLocation(location.getId(), location.getLocation().toText(), location.getName());
	}

}
