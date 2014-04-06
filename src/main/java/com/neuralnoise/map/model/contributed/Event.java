package com.neuralnoise.map.model.contributed;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Event extends ContributedEntity {

	private static final long serialVersionUID = 7121988712295223558L;

	private static final Logger log = LoggerFactory.getLogger(Event.class);
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	protected Calendar startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	protected Calendar endDate;

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
