package com.neuralnoise.map.model.map;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class Artisan extends ContributedEntity {

	private static final long serialVersionUID = -3414460840023103502L;

	private static final Logger log = LoggerFactory.getLogger(Artisan.class);

}
