package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.service.map.MAPService;

@Controller
@RequestMapping("/event")
public class EventController {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private MAPService mapService;
	
}