package com.neuralnoise.map.model.map;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Organization extends ContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Organization.class);

}
