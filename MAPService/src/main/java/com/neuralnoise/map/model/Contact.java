package com.neuralnoise.map.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Contact extends BaseEntity {

	private static final Logger log = LoggerFactory.getLogger(Contact.class);
	
	@Column(name = "email")
	protected String email;
	
	@Column(name = "phone")
	protected String phone;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
