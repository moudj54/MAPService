package com.neuralnoise.map.data.map;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.map.Event;
import com.vividsolutions.jts.geom.Point;

@Repository
@Transactional
public class EventDAO extends AbstractContributedDAO<Event, Long> {

	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);

	public EventDAO() {
		super(Event.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> findBetween(DateTime startDate, DateTime endDate) {
		Session session = getEntityManager().unwrap(Session.class);

		Criteria criteria = session.createCriteria(clazz).add(Restrictions.disjunction()
				.add(Restrictions.between("startDate", startDate, endDate))
				.add(Restrictions.between("endDate", startDate, endDate)));
		
		return criteria.list();
	}

}
