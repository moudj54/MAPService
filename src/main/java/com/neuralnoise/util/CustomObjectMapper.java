package com.neuralnoise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neuralnoise.geo.json.GeometryDeserializer;
import com.neuralnoise.geo.json.GeometrySerializer;
import com.vividsolutions.jts.geom.Geometry;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -2314203051178022125L;

	public CustomObjectMapper() {
		SimpleModule module = new SimpleModule("GeoJSONModule");
		
		module.addSerializer(Geometry.class, new GeometrySerializer());
		module.addDeserializer(Geometry.class, new GeometryDeserializer());
		
		this.registerModule(module);
	}
	
}
