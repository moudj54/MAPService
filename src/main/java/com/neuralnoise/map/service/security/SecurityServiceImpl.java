package com.neuralnoise.map.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.security.SecurityDAO;
import com.neuralnoise.map.model.security.UserEntity;

@Service("securityService")
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService {

	private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Autowired
	private SecurityDAO securityDAO;

	@Autowired
	private Assembler assembler;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Checking user {}", username);
		UserEntity ue = securityDAO.getById(username);
		if (ue == null)
			throw new UsernameNotFoundException("User \"" + username + "\" not found");
		return assembler.buildUserFromUserEntity(ue);
	}

	@Override
	@Transactional(readOnly = true)
	public UserEntity current() throws UsernameNotFoundException {

		UserEntity ue = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			ue = securityDAO.getById(userDetails.getUsername());

			if (ue == null)
				throw new UsernameNotFoundException("User \"" + userDetails.getUsername() + "\" not found");
		}

		return ue;
	}
}