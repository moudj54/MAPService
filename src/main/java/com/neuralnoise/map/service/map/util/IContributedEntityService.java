package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.map.AbstractContributedEntity;

public interface IContributedEntityService<T extends AbstractContributedEntity> extends INamedEntityService<T> {

	public List<T> findByContributor(String name);

}
