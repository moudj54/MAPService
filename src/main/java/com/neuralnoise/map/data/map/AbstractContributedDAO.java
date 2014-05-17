package com.neuralnoise.map.data.map;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.data.AbstractNamedDAO;
import com.neuralnoise.map.model.map.AbstractContributedEntity;

@Repository
public abstract class AbstractContributedDAO<T extends AbstractContributedEntity, I> extends AbstractNamedDAO<T, I> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedDAO.class);

	public AbstractContributedDAO(Class<T> clazzToSet) {
		super(clazzToSet);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByContributor(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.contributor.name = :name");
		return query.setParameter("name", name).getResultList();
	}
	
}
