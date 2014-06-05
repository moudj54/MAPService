package com.neuralnoise.integration.util;

import java.io.Serializable;
import java.util.List;

public class CAnswer implements Serializable {

	private static final long serialVersionUID = 5019596129437896095L;

	protected CRequest request;
	protected List<CEvent> events;

	public CAnswer() { }
	
	public CRequest getRequest() {
		return request;
	}

	public void setRequest(CRequest request) {
		this.request = request;
	}
	
	public List<CEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CEvent> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "CAnswer [request=" + request + ", events=" + events + "]";
	}
	
}
