package com.neuralnoise.map.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuralnoise.map.data.SecurityDAO;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.Assembler;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private SecurityDAO securityDAO;

	@Autowired
	private Assembler assembler;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		log.info("Querying for user {}, dao {}, assembler {}", username, securityDAO, assembler);
		
		UserEntity userEntity = securityDAO.getById(username);
		if (userEntity == null)
			throw new UsernameNotFoundException("user not found");

		return assembler.buildUserFromUserEntity(userEntity);
	}
}