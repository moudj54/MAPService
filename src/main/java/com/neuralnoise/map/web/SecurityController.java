package com.neuralnoise.map.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.neuralnoise.map.service.security.SecurityService;

@Controller
public class SecurityController {

	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private SecurityService securityService;
	
}
