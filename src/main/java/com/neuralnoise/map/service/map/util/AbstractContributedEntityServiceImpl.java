package com.neuralnoise.map.service.map.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.map.AbstractContributedDAO;
import com.neuralnoise.map.model.map.AbstractContributedEntity;
import com.neuralnoise.map.model.security.UserEntity;

public abstract class AbstractContributedEntityServiceImpl<T extends AbstractContributedEntity, D extends AbstractContributedDAO<T, Long>> extends AbstractNamedEntityServiceImpl<T, D> implements IContributedEntityService<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractContributedEntityServiceImpl.class);

	@Override
	@Transactional(readOnly = false)
	public T create(T event) {
		boolean authorized = false;

		UserEntity ue = securityService.current();

		if (ue != null) {
			authorized = true;
		}

		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}

		event.setContributor(ue);
		return entityDAO.create(event);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		boolean authorized = false;

		UserEntity ue = securityService.current();

		if (ue != null) {
			if (ue.isAdmin()) {
				authorized = true;
			} else {
				T e = entityDAO.getById(id);
				if (e != null) {
					if (e.getContributor().getName().equals(ue.getName())) {
						authorized = true;
					}
				}
			}
		}

		if (!authorized) {
			throw new InsufficientAuthenticationException("Insufficient privileges");
		}

		entityDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findByContributor(String name) {
		return entityDAO.findByContributor(name);
	}

}
