package com.neuralnoise.map.web;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.model.Pizza;
import com.neuralnoise.map.model.security.UserEntity;
import com.neuralnoise.map.service.security.SecurityService;

@Controller
@RequestMapping("/security")
public class SecurityController {

	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	UserEntity current() {
		log.info("Returning user details ..");
		return securityService.current();
	}
	
}
