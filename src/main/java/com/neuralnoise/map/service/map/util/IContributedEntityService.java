package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.map.AbstractContributedEntity;

public interface IContributedEntityService<T extends AbstractContributedEntity> extends IEntityService<T> {
	
	public List<T> findByName(String name);
	
	public List<T> findByContributor(String name);
	
}
