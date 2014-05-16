package com.neuralnoise.map.service.test;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.service.geo.GeoLocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/web-context.xml")
public class GeoLocationServiceTests {

	@Autowired
	private GeoLocationService geoService;

	@Test
	public void testSayHello() throws IOException {
		List<Location> bari = geoService.lookupGoogle("Bari");
		//Assert.assertEquals("Hello world!", helloService.sayHello());
	}
}