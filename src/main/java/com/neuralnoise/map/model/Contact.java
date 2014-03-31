package com.neuralnoise.map.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Contact extends BaseEntity {

	private static final Logger log = LoggerFactory.getLogger(Contact.class);
	
	@Column(name = "email")
    @NotEmpty(message = "Email address cannot be empty")
    @Email(message = "Invalid email address, e.g. valid email address: example@gmail.com")
	protected String email;

    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12, message = "Incorrect Format, valid e.g. 121212121212")
    @Column(name = "phone_number")
	protected String phoneNumber;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
