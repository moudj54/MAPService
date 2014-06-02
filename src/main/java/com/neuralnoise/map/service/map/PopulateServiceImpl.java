package com.neuralnoise.map.service.map;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.integration.RequestsGateway;
import com.neuralnoise.integration.util.CRequest;
import com.neuralnoise.map.data.map.ArtisanDAO;
import com.neuralnoise.map.data.map.OrganizationDAO;
import com.neuralnoise.map.model.geo.Address;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.Event;
import com.neuralnoise.map.model.map.Museum;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.geo.GeoLocationService;
import com.neuralnoise.map.service.map.util.EntityParser;
import com.neuralnoise.map.service.map.util.IContributedEntityService;
import com.neuralnoise.map.service.map.util.XSSFParser;
import com.neuralnoise.map.service.security.SecurityService;

@Service
public class PopulateServiceImpl implements PopulateService, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(PopulateServiceImpl.class);

	/* 
	 * Utility services: geoLocation and Security
	 */
	@Autowired
	private GeoLocationService geoLocationService;
	@Autowired
	private SecurityService securityService;
	
	/*
	 * Primitive Types used to describe Domain Entities
	 */
	@Autowired
	private AddressService addressService;
	@Autowired
	private LocationService locationService;
	
	/*
	 * Domain Entities (Artisans, Organizations, Museums, Events)
	 */
	@Autowired
	private ArtisanService artisanService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private MuseumService museumService;
	@Autowired
	private EventService eventService;
	
	private ApplicationContext applicationContext;

	private IContributedEntityService<? extends AbstractContributedEntity> getService(AbstractContributedEntity entity) {
		IContributedEntityService<? extends AbstractContributedEntity> service = null;
		if (entity != null) {
			if (entity instanceof Artisan) {
				service = artisanService;
			} else if (entity instanceof Organization) {
				service = organizationService;
			} else if (entity instanceof Museum) {
				service = museumService;
			} else if (entity instanceof Event) {
				service = eventService;
			}
		}
		return service;
	}

	@Override
	@Transactional(readOnly = false)
	public void clean() throws Exception {
		for (Artisan artisan : artisanService.getAll())
			artisanService.deleteById(artisan.getId());
		for (Organization organization : organizationService.getAll())
			organizationService.deleteById(organization.getId());
		for (Museum museum : museumService.getAll())
			museumService.deleteById(museum.getId());
		for (Event event : eventService.getAll())
			eventService.deleteById(event.getId());
		
		for (Location location : locationService.getAll())
			locationService.deleteById(location.getId());
		for (Address address : addressService.getAll())
			addressService.deleteById(address.getId());
	}

	@Override
	@Transactional(readOnly = false)
	public void populate(String path) throws Exception {
		UserEntity user = securityService.current();
		if (user == null || !user.isAdmin()) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		List<Map<String, String>> content = XSSFParser.parse(new File(path));
		for (Map<String, String> map : content) {
			AbstractContributedEntity entity = EntityParser.parse(map, geoLocationService);
			IContributedEntityService<? extends AbstractContributedEntity> service = getService(entity);
			if (service != null && entity.getLocation() != null) {
				List<? extends AbstractContributedEntity> existingEntities = service.findByName(entity.getName());
				AbstractContributedEntity existingEntity = (existingEntities.size() > 0 ? existingEntities.get(0) : null);
				if (existingEntity != null) {
					log.info("Existing entity: " + existingEntity);
				} else {
					log.info("Memorizing entity: " + entity);
					Location location = entity.getLocation();
					List<Location> existingLocations = locationService.findByName(location.getName());
					Location existingLocation = (existingLocations.size() > 0 ? existingLocations.get(0) : null);
					if (existingLocation == null) {
						log.info("Memorizing location: " + location);
						location = locationService.create(location);
					} else {
						entity.setContributor(user);
						entity.setLocation(existingLocation);
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
	}

	@Override
	public void request(String adapterName, String resource) {
		RequestsGateway gateway = (RequestsGateway) applicationContext.getBean("requestsGateway");
		CRequest request = new CRequest();
		request.setAdapterName(adapterName);
		request.setResource(resource);
		log.info("Sending request: " + request);
		gateway.sendMessage(request);
	}

	/*
	 * every day at 10 AM, from http://www.cronmaker.com/
	 */
	@Override
	@Scheduled(cron="0 0 10 1/1 * ? *")
	public void collect() {
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/4");
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/7");
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/9");
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
