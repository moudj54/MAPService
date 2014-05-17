package com.neuralnoise.map.web.map;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.service.map.PopulateService;

@Controller
@RequestMapping("/populate")
public class PopulateController {

	private static final Logger log = LoggerFactory.getLogger(PopulateController.class);
	
	@Autowired
	private PopulateService service;
	
	@RequestMapping(value = "/{path}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void populate(@PathVariable("path") String path) throws IOException {
		log.info("Populating the DB from path {} ..", path);
		service.populate(path);
	}
	
}
