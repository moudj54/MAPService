package com.neuralnoise.map.data;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.contributed.Event;

@Repository
@Transactional
public class EventDAO extends AbstractDAO<Event, Long> {

	public EventDAO() {
		super(Event.class);
	}

	public List<Event> findByName(String name) {
		Query query = getEntityManager().createQuery("from " + clazz.getName() + " e where e.name = :name");
		return query.setParameter("name", name).getResultList();
	}
	
}
