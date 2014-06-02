package com.neuralnoise.integration.util;

import org.joda.time.DateTime;

import com.neuralnoise.integration.geo.Location;

public class CEvent extends CNamedEntry {

	protected DateTime startDate, endDate;
	protected Location location;

	public CEvent() {
		super();
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
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
