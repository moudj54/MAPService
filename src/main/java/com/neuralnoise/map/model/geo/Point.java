package com.neuralnoise.map.model.geo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuralnoise.geo.json.GeoJSONDeserializer;
import com.neuralnoise.geo.json.GeoJSONSerializer;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

@JsonSerialize(using = GeoJSONSerializer.class)
@JsonDeserialize(using = GeoJSONDeserializer.class)
public class Point extends com.vividsolutions.jts.geom.Point {

	private static final long serialVersionUID = 5780905353289668789L;

	public Point(CoordinateSequence coordinates, GeometryFactory factory) {
		super(coordinates, factory);
	}
	
	public Point(com.vividsolutions.jts.geom.Point p) {
		this(p.getCoordinateSequence(), p.getFactory());
	}

}
