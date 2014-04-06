package com.neuralnoise.map.service.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.neuralnoise.map.model.security.UserEntity;

public interface SecurityService extends UserDetailsService {

	UserEntity current() throws UsernameNotFoundException;
	
}
