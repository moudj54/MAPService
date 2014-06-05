package com.neuralnoise.integration.util;

import com.neuralnoise.integration.geo.Location;

public abstract class CEntity extends CNamedEntry {

	protected Location location;
	protected String type;
	
	public CEntity() {
		super();
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
