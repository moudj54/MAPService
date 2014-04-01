package com.neuralnoise.map.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.SecurityDAO;
import com.neuralnoise.map.model.security.UserEntity;

@Service("securityService")
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService, UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Autowired
	private SecurityDAO securityDAO;
	
	@Autowired
	private Assembler assembler;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Checking user {}, dao {}, assembler {}", username, securityDAO, assembler);
		
		UserEntity ue = securityDAO.getById(username);
		log.info("ue: {}", ue);
		
		if (ue == null)
			 throw new UsernameNotFoundException("User \"" + username + "\" not found");
		
		return assembler.buildUserFromUserEntity(ue);
	}
}