package com.neuralnoise.map.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(BaseEntity.class);
	
	private static final long serialVersionUID = 1134332795854462829L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

}