package com.neuralnoise.map.model.geo.util;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.geo.Location;

public class LocationDeserializer extends JsonDeserializer<Location> {

	private static final Logger log = LoggerFactory.getLogger(LocationDeserializer.class);
	
	@Override
	public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		
		log.info("Node: " + node);
		
		final Double latitude = node.get("latitude").getDoubleValue(), longitude = node.get("longitude").getDoubleValue();
		final String name = node.get("name").getTextValue();
		
		log.info("Creating new Location({}, {}, {}) ..", latitude, longitude, name);
		Location location = new Location(latitude, longitude, name);
		log.info("Created object: " + location);
		
		return location;
	}

}
