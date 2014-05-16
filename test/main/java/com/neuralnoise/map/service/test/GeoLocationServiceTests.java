package com.neuralnoise.map.service.test;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/web-context.xml")
public class GeoLocationServiceTests {

	private static final Logger log = LoggerFactory.getLogger(GeoLocationServiceTests.class);
	
	@Autowired
	private GeoLocationService geoService;

	@Test
	public void testSayHello() throws IOException {
		
		log.info("Looking up 'Bari' using Google ..");
		List<Location> bari = geoService.lookupGoogle("Bari");
		//Assert.assertEquals("Hello world!", helloService.sayHello());
	}
}