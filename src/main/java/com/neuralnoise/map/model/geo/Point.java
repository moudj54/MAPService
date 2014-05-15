package com.neuralnoise.map.model.geo;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.neuralnoise.geo.json.GeometryDeserializer;
import com.neuralnoise.geo.json.GeometrySerializer;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;

@JsonSerialize(using = GeometrySerializer.class)
@JsonDeserialize(using = GeometryDeserializer.class)
public class Point extends com.vividsolutions.jts.geom.Point {

	private static final long serialVersionUID = 5780905353289668789L;

	public Point(CoordinateSequence coordinates, GeometryFactory factory) {
		super(coordinates, factory);
	}

}
