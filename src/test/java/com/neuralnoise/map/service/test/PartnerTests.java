package com.neuralnoise.map.service.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.test.util.EntityParser;
import com.neuralnoise.map.service.test.util.XSSFParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/web-context.xml")
public class PartnerTests {

	private static final Logger log = LoggerFactory.getLogger(PartnerTests.class);
	
	@Autowired
	private GeoLocationService geoService;
	
	private static final String[]  RESS = {
		"/partner01/PARTNER.xlsx", "/art01/schede_v.3.xlsx"
	};

	@Test
	public void test() throws IOException {
		for (String res : RESS) {
			String path = ClassLoader.class.getResource(res).getPath();
			List<Map<String, String>> content = XSSFParser.parse(new File(path));

			for (Map<String, String> map : content) {
				log.info("Content: " + map);
				AbstractContributedEntity entity = EntityParser.parse(map, geoService);
				log.info("Entity: " + entity);
			}
		}
	}
}
