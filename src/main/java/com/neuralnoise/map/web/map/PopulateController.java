package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuralnoise.map.service.map.PopulateService;

@Controller
@RequestMapping("/populate")
public class PopulateController {

	private static final Logger log = LoggerFactory.getLogger(PopulateController.class);
	
	@Autowired
	private PopulateService service;
	
	@RequestMapping(params = { "path" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void populate(@RequestParam("path") String path) throws Exception {
		log.info("Populating the DB from path {} ..", path);
		service.populate(path);
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void test() throws Exception {
		service.test();
	}
	
}
