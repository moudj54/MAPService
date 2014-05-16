package com.neuralnoise.map.model.geo.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.neuralnoise.map.model.geo.Location;

public class LocationDeserializer extends JsonDeserializer<Location> {

	private static final Logger log = LoggerFactory.getLogger(LocationDeserializer.class);
	
	@Override
	public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		
		log.info("Node: " + node);
		
		final Double latitude = node.get("latitude").doubleValue(), longitude = node.get("longitude").doubleValue();
		final String name = node.get("name").textValue();
		
		log.info("Creating new Location({}, {}, {}) ..", latitude, longitude, name);
		Location location = new Location(latitude, longitude, name);
		log.info("Created object: " + location);
		
		return location;
	}

}
