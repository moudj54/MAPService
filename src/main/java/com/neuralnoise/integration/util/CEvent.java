package com.neuralnoise.integration.util;

import java.util.Calendar;

import com.neuralnoise.integration.geo.Location;

public class CEvent extends CNamedEntry {

	protected Calendar startDate, endDate;
	protected Location location;

	public CEvent() {
		super();
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "CEvent [startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + ", content=" + content + "]";
	}
	
}
