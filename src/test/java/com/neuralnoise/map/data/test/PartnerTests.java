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

import com.neuralnoise.map.data.LocationDAO;
import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.data.map.ArtisanDAO;
import com.neuralnoise.map.data.map.OrganizationDAO;
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
import com.vividsolutions.jts.geom.Point;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {
			"classpath:META-INF/spring/web-context.xml",
			"classpath:META-INF/spring/servlet-context.xml"	
		}
)
public class PartnerTests {

	private static final Logger log = LoggerFactory.getLogger(PartnerTests.class);
	
	@Autowired
	private GeoLocationService geoService;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired
	private ArtisanDAO artisanDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	private static final String[] RESS = {
		"/partner01/PARTNER.xlsx", "/art01/schede_v.3.xlsx"
	};

	private AbstractContributedDAO<? extends AbstractContributedEntity, ?> getService(AbstractContributedEntity entity) {
		AbstractContributedDAO<? extends AbstractContributedEntity, ?> service = null;
		if (entity != null) {
			if (entity instanceof Artisan) {
				service = artisanDAO;
			} else if (entity instanceof Organization) {
				service = organizationDAO;
			}
		}
		return service;
	}
	
	@Test
	public void test() throws IOException {
		for (String res : RESS) {
			String path = ClassLoader.class.getResource(res).getPath();
			List<Map<String, String>> content = XSSFParser.parse(new File(path));

			for (Location location : locationDAO.getAll()) {
				locationDAO.deleteById(location.getId());
			}
			
			for (Artisan artisan : artisanDAO.getAll()) {
				artisanDAO.deleteById(artisan.getId());
			}
			
			for (Organization organization : organizationDAO.getAll()) {
				organizationDAO.deleteById(organization.getId());
			}
			
			for (Map<String, String> map : content) {
				AbstractContributedEntity entity = EntityParser.parse(map, geoService);
				
				log.info("Memorizing entity: " + entity);
				
				AbstractContributedDAO<? extends AbstractContributedEntity, ?> service = getService(entity);

				if (service != null && entity.getLocation() != null) {
					Location location = entity.getLocation();
					Point point = location.getPoint();
					
					log.info("Memorizing location: " + location);
					location = locationDAO.create(point.getX(), point.getY(), location.getName());

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
