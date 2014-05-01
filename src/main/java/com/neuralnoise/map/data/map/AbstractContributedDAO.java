package com.neuralnoise.map.data.map;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.common.base.Preconditions;
import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.map.AbstractContributedEntity;

@Repository
public abstract class AbstractContributedDAO<T extends AbstractContributedEntity, I> extends AbstractDAO<T, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedDAO.class);
	
	public AbstractContributedDAO(Class<T> clazzToSet) {
		super(clazzToSet);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByName(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.name = :name");
		return query.setParameter("name", name).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByContributor(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.contributor.name = :name");
		return query.setParameter("name", name).getResultList();
	}

	public T create(final T entity) {
		Preconditions.checkNotNull(entity);
		getEntityManager().persist(entity);
		return entity;
	}
	
}
