package com.neuralnoise.map.model.map;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuralnoise.map.model.AbstractNamedEntity;
import com.neuralnoise.map.model.geo.Address;
import com.neuralnoise.map.model.geo.Location;
import com.neuralnoise.map.model.security.UserEntity;

@MappedSuperclass
public abstract class AbstractContributedEntity extends AbstractNamedEntity {

	private static final long serialVersionUID = -4161533353336890677L;

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedEntity.class);

	@NotNull
	@ManyToOne
	protected UserEntity contributor;
	
	@Column(name = "description")
	protected String description;
	
	@NotNull
	@ManyToOne
	protected Address address;

	@NotNull
	@ManyToOne
	protected Location coordinates;

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
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Location getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Location coordinates) {
		this.coordinates = coordinates;
	}
	
}
