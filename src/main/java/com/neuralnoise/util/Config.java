package com.neuralnoise.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.support.json.JsonObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.neuralnoise.map.model.geo.json.GeometryDeserializer;
import com.neuralnoise.map.model.geo.json.GeometrySerializer;
import com.vividsolutions.jts.geom.Geometry;

@Configuration
public class Config {

	@Bean
	public JsonObjectMapper<?, ?> xObjectMapper() {
		final ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("MAPJSONModule");
		module.addSerializer(Geometry.class, new GeometrySerializer());
		module.addDeserializer(Geometry.class, new GeometryDeserializer());
		mapper.registerModule(module);
		mapper.registerModule(new JodaModule());
		return new Jackson2JsonObjectMapper(mapper);
	}

}