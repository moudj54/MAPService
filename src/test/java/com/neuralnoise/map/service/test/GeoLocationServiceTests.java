package com.neuralnoise.map.service.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.vividsolutions.jts.geom.Point;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/web-context.xml")
public class GeoLocationServiceTests {

	private static final Logger log = LoggerFactory.getLogger(GeoLocationServiceTests.class);
	
	@Autowired
	private GeoLocationService geoService;

	@Test
	public void testGoogle() throws IOException {
		log.info("Looking up 'Bari' using Google ..");
		List<Location> locations = geoService.lookupGoogle("Bari, Puglia");
		for (Location location : locations) {
			log.info("Location: " + location);
			final double x = 16.8718715, y = 41.1171432;
			Point point = location.getLocation();
			assertTrue(Math.abs(x - point.getX()) < 1e-2);
			assertTrue(Math.abs(y - point.getY()) < 1e-2);
		}
	}
	
	@Test
	public void testNominatim() throws IOException {
		log.info("Looking up 'Bari' using Nominatim ..");
		List<Location> locations = geoService.lookupNominatim("Bari, Puglia");
		for (Location location : locations) {
			log.info("Location: " + location);
			final double x = 16.8718715, y = 41.1171432;
			if (location.getName().equals("Bari, BA, PUG, Italia")) {
				Point point = location.getLocation();
				assertTrue(Math.abs(x - point.getX()) < 1e-2);
				assertTrue(Math.abs(y - point.getY()) < 1e-2);
			}
		}
	}
}