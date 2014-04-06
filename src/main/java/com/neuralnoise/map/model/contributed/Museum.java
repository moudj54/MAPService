package com.neuralnoise.map.model.contributed;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Museum extends ContributedEntity {

	private static final Logger log = LoggerFactory.getLogger(Museum.class);

}
