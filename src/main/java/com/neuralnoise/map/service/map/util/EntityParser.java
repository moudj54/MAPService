package com.neuralnoise.map.service.map.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.model.map.Organization;
import com.neuralnoise.map.service.geo.GeoLocationService;

public class EntityParser {

	private static final Logger log = LoggerFactory.getLogger(EntityParser.class);
	
	private EntityParser() { }
	
	public static AbstractContributedEntity parse(Map<String, String> map, GeoLocationService geoService) throws IOException {
		AbstractContributedEntity entity = null;
		
		final String category = map.get("categoria");
		if (category == null)
			return entity;
		
		Pattern artisanPattern = Pattern.compile("Artigi|artigi|Resta|resta");
		Matcher artisanMatcher = artisanPattern.matcher(category);
		
		if (entity == null && artisanMatcher.find()) {
			entity = new Artisan();
		}
		
		Pattern organizationPattern = Pattern.compile("Azienda|azienda|Agroalimentare|agroalimentare");
		Matcher organizationMatcher = organizationPattern.matcher(category);
		
		if (entity == null && organizationMatcher.find()) {
			entity = new Organization();
		}

		final String name = map.get("nome");
		if (name != null && entity != null) {
			entity.setName(name);
		}
		
		final String description = map.get("descrizione");
		if (description != null && entity != null) {
			entity.setDescription(description);
		}

		if (entity == null) {
			log.warn("Map: " + map + ", entity: " + null);
		}
		
		
		final String address = map.get("indirizzo"), city = map.get("city");
		if ((address != null || city != null) && entity != null) {
			String str = (address != null ? address + " " : "") + (city != null ? city : "");
			str += ", Puglia";
			List<Location> locations = geoService.lookupGoogle(str);
			locations.addAll(geoService.lookupNominatim(str));
			
			if (locations.size() > 0) {
				entity.setLocation(locations.get(0));
			}
		}
		
		return entity;
	}
	
}
