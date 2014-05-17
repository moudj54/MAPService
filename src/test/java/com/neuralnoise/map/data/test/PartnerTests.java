package com.neuralnoise.map.data.test;

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

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.map.ArtisanService;
import com.neuralnoise.map.service.map.LocationService;
import com.neuralnoise.map.service.map.MuseumService;
import com.neuralnoise.map.service.map.OrganizationService;
import com.neuralnoise.map.service.map.util.IContributedEntityService;
import com.neuralnoise.map.test.util.EntityParser;
import com.neuralnoise.map.test.util.XSSFParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/web-context.xml")
public class PartnerTests {

	private static final Logger log = LoggerFactory.getLogger(PartnerTests.class);
	
	@Autowired
	private GeoLocationService geoService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private ArtisanService artisanService;
	
	@Autowired
	private OrganizationService organizationService;
	
	private static final String[] RESS = {
		"/partner01/PARTNER.xlsx", "/art01/schede_v.3.xlsx"
	};

	private IContributedEntityService<? extends AbstractContributedEntity> getService(AbstractContributedEntity entity) {
		IContributedEntityService<? extends AbstractContributedEntity> service = null;
		if (entity != null) {
			if (entity instanceof Artisan) {
				service = artisanService;
			} else if (entity instanceof Organization) {
				service = organizationService;
			}
		}
		return service;
	}
	
	@Test
	public void test() throws IOException {
		for (String res : RESS) {
			String path = ClassLoader.class.getResource(res).getPath();
			List<Map<String, String>> content = XSSFParser.parse(new File(path));

			for (Map<String, String> map : content) {
				AbstractContributedEntity entity = EntityParser.parse(map, geoService);
				
				log.info("Memorizing entity: " + entity);
				
				IContributedEntityService<? extends AbstractContributedEntity> service = getService(entity);

				if (service != null) {
					Location location = entity.getLocation();
					location = locationService.create(location);

					if (service instanceof ArtisanService) {
						((ArtisanService) service).create((Artisan) entity);
					} else if (service instanceof OrganizationService) {
						((OrganizationService) service).create((Organization) entity);
					}
				}
			}
		}
	}
}
