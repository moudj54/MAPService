package com.neuralnoise.map.model.geo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.AbstractBaseEntity;

@Entity
@Table(name = "address")
public class Address extends AbstractBaseEntity {

	private static final Logger log = LoggerFactory.getLogger(Address.class);
	
	private static final long serialVersionUID = 1719316108553026232L;

	@Column(name = "street")
	protected String street;
	
	@Column(name = "postal_code")
	protected String postalCode;

	@Column(name = "city")
	protected String city;

	@Column(name = "country")
	protected String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	
}
