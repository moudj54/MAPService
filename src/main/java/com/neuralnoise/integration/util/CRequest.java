package com.neuralnoise.integration.util;

import java.io.Serializable;

public class CRequest implements Serializable {

	private static final long serialVersionUID = 5019596129437896095L;

	protected String resource, adapterName;

	public CRequest() { }
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public String getAdapterName() {
		return adapterName;
	}

	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}

	@Override
	public String toString() {
		return "CRequest [resource=" + resource + ", adapterName=" + adapterName + "]";
	}

}
