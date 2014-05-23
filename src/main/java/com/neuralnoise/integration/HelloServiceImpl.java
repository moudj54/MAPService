package com.neuralnoise.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {
	
	private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);
	
	@Override
	public void hello(String name) {
		log.info("Message received -- " + name);
	}
	
}