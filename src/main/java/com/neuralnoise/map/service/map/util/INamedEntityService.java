package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.AbstractNamedEntity;

public interface INamedEntityService<T extends AbstractNamedEntity> extends IEntityService<T> {

	public List<T> findByName(String name);

}
