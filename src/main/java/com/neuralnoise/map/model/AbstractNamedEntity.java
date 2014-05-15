package com.neuralnoise.map.model;

import java.util.Map;

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

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> properties = super.getProperties();
		properties.put("name", name);
		return properties;
	}
	
}
