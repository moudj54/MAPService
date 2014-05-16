package com.neuralnoise.map.model.geo.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.neuralnoise.map.model.geo.Location;
import com.vividsolutions.jts.geom.Point;

public class LocationSerializer extends JsonSerializer<Location> {

	private static final Logger log = LoggerFactory.getLogger(LocationSerializer.class);
	
	@Override
	public void serialize(Location value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

		Long id = value.getId();
		Point point = value.getLocation();
		String name = value.getName();

		log.info("Serializing id: " + id + ", point: " + point + ", name: " + name + " ..");
		
		jgen.writeStartObject();

		if (id != null) {
			jgen.writeFieldName("id");
			jgen.writeNumber(id);
		}

		if (point != null) {
			Double latitude = point.getY(), longitude = point.getX();
			
			log.info("Latitude: " + latitude + ", longitude: " + longitude);
			
			jgen.writeFieldName("latitude");
			jgen.writeNumber(latitude);

			jgen.writeFieldName("longitude");
			jgen.writeNumber(longitude);
		}
		
		jgen.writeFieldName("name");
		jgen.writeString(name);

		jgen.writeEndObject();
	}

}
