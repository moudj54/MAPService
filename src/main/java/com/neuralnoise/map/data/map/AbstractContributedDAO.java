package com.neuralnoise.map.data.map;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractDAO;
import com.neuralnoise.map.model.map.Event;

@Repository
public abstract class AbstractContributedDAO<T extends Serializable, I> extends AbstractDAO<T, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedDAO.class);
	
	public AbstractContributedDAO(Class<T> clazzToSet) {
		super(clazzToSet);
	}
	
	public List<T> findByName(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.name = :name");
		return query.setParameter("name", name).getResultList();
	}
	
	public List<T> findByContributor(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.contributor.name = :name");
		return query.setParameter("name", name).getResultList();
	}
	
}
