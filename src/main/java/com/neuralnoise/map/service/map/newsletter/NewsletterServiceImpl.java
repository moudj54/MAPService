package com.neuralnoise.map.service.map.newsletter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.newsletter.SubscriptionDAO;
import com.neuralnoise.map.model.map.newsletter.Subscription;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

@Service
public class NewsletterServiceImpl implements NewsletterService {

	private static final Logger log = LoggerFactory.getLogger(NewsletterServiceImpl.class);

	@Autowired
	protected SubscriptionDAO subscriptionDAO;

	@Autowired
	protected SecurityService securityService;
	
	@Override
	@Transactional(readOnly = false)
	public Subscription create(Subscription subscription) {
		boolean authorized = false;
		UserEntity ue = securityService.current();
		if (ue != null) {
			authorized = true;
		}
		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}
		return subscriptionDAO.create(subscription);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subscription> getAll() {
		return subscriptionDAO.getAll();
	}

}

