package com.neuralnoise.map.model.geo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.AbstractBaseEntity;

@Entity
@Table(name = "geographic_coordinates")
public class GeographicCoordinates extends AbstractBaseEntity {

	private static final Logger log = LoggerFactory.getLogger(GeographicCoordinates.class);
	
	private static final long serialVersionUID = 8809624185680983201L;

	@Column(name = "latitude")
	@NotEmpty
	protected Double latitude;

	@Column(name = "longitude")
	@NotEmpty
	protected Double longitude;
	
	@Column(name = "address")
	protected String address;

	public GeographicCoordinates(Double latitude, Double longitude, String address) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setAddress(address);
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
