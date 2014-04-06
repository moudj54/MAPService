package com.neuralnoise.map.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.service.MAPService;

@Controller
@RequestMapping("/")
public class MAPServiceController {

	private static final Logger log = LoggerFactory.getLogger(MAPServiceController.class);
	
}
