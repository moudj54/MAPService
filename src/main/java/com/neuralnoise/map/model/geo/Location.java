package com.neuralnoise.map.model.geo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuralnoise.map.model.AbstractNamedEntity;
import com.neuralnoise.map.model.geo.util.LocationDeserializer;
import com.neuralnoise.map.model.geo.util.LocationSerializer;
import com.vividsolutions.jts.geom.Geometry;
//import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
/*
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
*/

@Entity
@Table(name = "location")
@JsonSerialize(using = LocationSerializer.class)
@JsonDeserialize(using = LocationDeserializer.class)
public class Location extends AbstractNamedEntity {

	private static final Logger log = LoggerFactory.getLogger(Location.class);

	private static final long serialVersionUID = 8809624185680983201L;

	@Column(name = "location")
	@Type(type = "org.hibernate.spatial.GeometryType")
	//@JsonTypeInfo(use = Id.NAME)
	protected Point location;

	private static Geometry toGeometry(String wktPoint) {
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		return geom;
	}

	public Location() { }

	public Location(Double latitude, Double longitude, String address) {
		this();
		this.setLocation(latitude, longitude);
		this.setName(address);
	}
	
	public Location(Point location, String address) {
		this();
		this.setLocation(location);
		this.setName(address);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void setLocation(Double latitude, Double longitude) {
		this.location = new Point((com.vividsolutions.jts.geom.Point) toGeometry("POINT(" + longitude + " " + latitude + ")"));
	}
	
	@Override
	public String toString() {
		return "Location [location=" + location + ", name=" + name + ", id=" + id + "]";
	}

}
