package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.map.Event;

public interface IEntityService<T extends AbstractContributedEntity> {

	public T getById(Long id);
	
	public T create(T entity);
	
	public List<T> getAll();
	
	public List<T> findByName(String name);
	
	public List<T> findByContributor(String name);
	
}
