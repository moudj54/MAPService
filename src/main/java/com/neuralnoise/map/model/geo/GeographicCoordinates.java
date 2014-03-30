package com.neuralnoise.map.model.geo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.BaseEntity;

@MappedSuperclass
public class GeographicCoordinates extends BaseEntity {

	private static final Logger log = LoggerFactory.getLogger(GeographicCoordinates.class);

	@Column(name = "latitude")
	@NotEmpty
	protected Long latitude;

	@Column(name = "longitude")
	@NotEmpty
	protected Long longitude;

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

}
