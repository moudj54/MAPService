package com.neuralnoise.map.model.map;

import java.util.Calendar;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "event")
public class Event extends AbstractContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Event.class);

	private static final long serialVersionUID = 7121988712295223558L;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	@NotNull
	protected Calendar startDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	@NotNull
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
	
	@Override
	public Map<String, String> getProperties() {
		Map<String, String> properties = super.getProperties();
		properties.put("from", startDate.toString());
		properties.put("from", endDate.toString());
		return properties;
	}

}
