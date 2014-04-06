package com.neuralnoise.map.service.map.util;

import java.util.List;

import com.neuralnoise.map.model.map.AbstractContributedEntity;

public interface IEntityService<T extends AbstractContributedEntity> {

	public T getById(Long id);
	
	public T create(T entity);
	
	public List<T> getAll();
	
	public List<T> findByName(String name);
	
	public List<T> findByContributor(String name);
	
}
