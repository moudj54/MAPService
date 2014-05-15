package com.neuralnoise.map.model.geo;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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

}
