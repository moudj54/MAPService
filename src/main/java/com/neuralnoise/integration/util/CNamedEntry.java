package com.neuralnoise.integration.util;

public abstract class CNamedEntry extends CEntry {

	private String name;
	
	public CNamedEntry() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
