package com.neuralnoise.map.model.map;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.NamedEntity;
import com.neuralnoise.map.model.security.UserEntity;

@MappedSuperclass
public abstract class ContributedEntity extends NamedEntity {

	private static final long serialVersionUID = -4161533353336890677L;

	private static final Logger log = LoggerFactory.getLogger(ContributedEntity.class);

	@NotNull
	@ManyToOne
	protected UserEntity contributor;

	@Column(name = "description")
	protected String description;
	
	public UserEntity getContributor() {
		return contributor;
	}

	public void setContributor(UserEntity contributor) {
		this.contributor = contributor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
