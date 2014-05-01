package com.neuralnoise.map.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

	private static final long serialVersionUID = -964173231862367581L;

	@Column(name = "name")
	@NotEmpty
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
