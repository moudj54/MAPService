package com.neuralnoise.map.model.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.Contact;
import com.neuralnoise.map.model.NamedEntity;

@MappedSuperclass
public class DomainEntity extends NamedEntity {

	private static final Logger log = LoggerFactory.getLogger(DomainEntity.class);
	
	@Column(name = "contact")
	@NotEmpty
	protected Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}
