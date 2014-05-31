package com.neuralnoise.integration.util;

import java.util.Calendar;

public class CEvent extends CNamedEntry {

	protected Calendar startDate, endDate;
	
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
	
}
