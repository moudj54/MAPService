package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.AbstractBaseEntity;

public interface IEntityService<T extends AbstractBaseEntity> {

	public T getById(Long id);

	public T create(T entity);

	public List<T> getAll();

	public void deleteById(Long id);

}
