package com.neuralnoise.map.model.map;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@Column(name = "description", length = 8192)
	protected String description;

	//@NotNull
	@ManyToOne
	protected Address address;

	//@NotNull
	@ManyToOne
	protected Location location;

	public UserEntity getContributor() {
		return contributor;
	}

	public void setContributor(UserEntity contributor) {
		this.contributor = contributor;
	}	@JsonIgnore

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Override
	@JsonIgnore
	public Map<String, String> getProperties() {
		Map<String, String> properties = super.getProperties();
		if (description != null) {
			properties.put("description", description);
		}
		if (address != null) {
			if (address.getStreet() != null) {
				properties.put("address", address.getStreet());
			}
		}
		return properties;
	}
	
	@Override
	public String toString() {
		return "AbstractContributedEntity [contributor=" + contributor + ", description=" + description + ", address=" + address + ", location=" + location + ", name=" + name + ", id=" + id + "]";
	}

}
