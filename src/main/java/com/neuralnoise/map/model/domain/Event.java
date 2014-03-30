package com.neuralnoise.map.model.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Event extends DomainEntity {

	private static final Logger log = LoggerFactory.getLogger(Event.class);

	@Column(name = "description")
	protected String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	protected Calendar startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	protected Calendar endDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
