package com.neuralnoise.util;

import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neuralnoise.map.model.geo.json.GeometryDeserializer;
import com.neuralnoise.map.model.geo.json.GeometrySerializer;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.model.security.json.UserEntitySerializer;
import com.vividsolutions.jts.geom.Geometry;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -2314203051178022125L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("MAPJSONModule");
		
		module.addSerializer(Geometry.class, new GeometrySerializer());
		module.addSerializer(UserEntity.class, new UserEntitySerializer());
		
		module.addDeserializer(Geometry.class, new GeometryDeserializer());
		
		this.registerModule(module);
	}

	public static Jackson2JsonObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("MAPJSONModule");
		module.addSerializer(Geometry.class, new GeometrySerializer());
		module.addDeserializer(Geometry.class, new GeometryDeserializer());
		mapper.registerModule(module);
		return new Jackson2JsonObjectMapper(mapper);
	}

}
