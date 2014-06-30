package com.neuralnoise.integration.util;

import java.util.Set;

import com.neuralnoise.integration.geo.Location;

public abstract class CEntity extends CNamedEntry {

	protected Location location;
	protected String type;
	protected Set<String> annotations;
	
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
	
	public Set<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<String> annotations) {
		this.annotations = annotations;
	}
	
}
