package com.neuralnoise.integration.util;

import java.io.Serializable;

public class CRequest implements Serializable {

	private static final long serialVersionUID = 5019596129437896095L;

	protected String resource, adapter;

	public CRequest() { }
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public String getAdapter() {
		return adapter;
	}

	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}
	
	@Override
	public String toString() {
		return "CRequest [resource=" + resource + ", adapter=" + adapter + "]";
	}
	
}
