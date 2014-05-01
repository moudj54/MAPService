package com.neuralnoise.map.model.geo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.spatial.GeometryType;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.AbstractBaseEntity;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Entity
@Table(name = "location")
public class Location extends AbstractBaseEntity {

	private static final Logger log = LoggerFactory.getLogger(Location.class);
	
	private static final long serialVersionUID = 8809624185680983201L;

	@Column(name = "location")
	@Type(type="org.hibernate.spatial.GeometryType")
	protected com.vividsolutions.jts.geom.Point location;

	@Column(name = "name")
	protected String name;

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
	
	public Location(Double latitude, Double longitude, String address) {
		this((Point) toGeometry("POINT(" + latitude + ", " + longitude + ")"), address);
	}
	
	public Location(Point location, String address) {
		this.setLocation(location);
		this.setName(address);
	}
	
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String address) {
		this.name = address;
	}

}
