package com.neuralnoise.map.service.map;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.AddressDAO;
import com.neuralnoise.map.data.LocationDAO;
import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.data.map.ArtisanDAO;
import com.neuralnoise.map.data.map.OrganizationDAO;
import com.neuralnoise.map.data.security.SecurityDAO;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.map.util.EntityParser;
import com.neuralnoise.map.service.map.util.XSSFParser;
import com.neuralnoise.map.service.security.SecurityService;

@Service
public class PopulateServiceImpl implements PopulateService {

	private static final Logger log = LoggerFactory.getLogger(PopulateServiceImpl.class);
	
	@Autowired
	private GeoLocationService geoService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AddressDAO addressDAO;
	
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

	@Transactional(readOnly = false)
	public void populate(String path) throws IOException {
		
		UserEntity admin = securityService.current();
		if (admin == null || !admin.isAdmin()) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		
		List<Map<String, String>> content = XSSFParser.parse(new File(path));

		/*
		for (Location location : locationDAO.getAll()) {
			locationDAO.deleteById(location.getId());
		}
		
		for (Artisan artisan : artisanDAO.getAll()) {
			artisanDAO.deleteById(artisan.getId());
		}
		
		for (Organization organization : organizationDAO.getAll()) {
			organizationDAO.deleteById(organization.getId());
		}
		*/
		
		for (Map<String, String> map : content) {
			AbstractContributedEntity entity = EntityParser.parse(map, geoService);

			AbstractContributedDAO<? extends AbstractContributedEntity, ?> service = getService(entity);

			if (service != null && entity.getLocation() != null) {
				log.info("Memorizing entity: " + entity);

				Location location = entity.getLocation();

				log.info("Memorizing location: " + location);
				location = locationDAO.create(location);

				entity.setContributor(admin);
				entity.setLocation(location);

				if (service instanceof ArtisanDAO) {
					Artisan artisan = ((ArtisanDAO) service).create((Artisan) entity);
					log.info("Artisan: " + artisan);
				} else if (service instanceof OrganizationDAO) {
					Organization organization = ((OrganizationDAO) service).create((Organization) entity);
					log.info("Organization: " + organization);
				}
			}
		}
	}

}
