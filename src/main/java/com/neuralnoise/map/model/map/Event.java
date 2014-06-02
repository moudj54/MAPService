package com.neuralnoise.map.model.map;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "event")
public class Event extends AbstractContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Event.class);

	private static final long serialVersionUID = 7121988712295223558L;

	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	@NotNull
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime startDate;

	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	@NotNull
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime endDate;

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
	public Map<String, String> getProperties() {
		Map<String, String> properties = super.getProperties();
		properties.put("startDate", startDate.toString());
		properties.put("endDate", endDate.toString());
		return properties;
	}
	
	@Override
	public String toString() {
		return "Event [startDate=" + startDate + ", endDate=" + endDate + ", contributor=" + contributor + ", description=" + description + ", location=" + location + ", name=" + name + ", id=" + id + "]";
	}

}
