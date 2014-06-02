package com.neuralnoise.map.web.util;

import java.util.Map;

import com.vividsolutions.jts.geom.Point;

public class Feature {

	private final String type;
	private final Map<String, String> properties;
	private final Point geometry;

	public Feature(Map<String, String> properties, Point geometry) {
		this.type = "Feature";
		this.properties = properties;
		this.geometry = geometry;
	}

	public String getType() {
		return type;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public Point getGeometry() {
		return geometry;
	}

}
