package com.neuralnoise.integration.util;

import org.joda.time.DateTime;

public class CEvent extends CEntity {

	protected DateTime startDate, endDate;

	public CEvent() {
		super();
		this.setType("event");
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
	
	@Override
	public String toString() {
		return "CEvent [startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + ", content=" + content + "]";
	}
	
}
