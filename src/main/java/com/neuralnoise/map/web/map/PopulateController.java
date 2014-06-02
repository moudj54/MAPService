package com.neuralnoise.map.web.map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public void populate(@RequestParam("path") String path, HttpServletResponse response) throws Exception {
		log.info("Populating the DB from path {} ..", path);
		service.populate(path);
		response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value = "request", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void request(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String adapterName = request.getParameter("adapterName");
		final String resource = request.getParameter("resource");
		service.request(adapterName, resource);
		response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value = "collect", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void collect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		service.collect();
		response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value = "clean", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void clean(HttpServletRequest request, HttpServletResponse response) throws Exception {
		service.clean();
		response.setStatus(HttpStatus.OK.value());
	}
	
}
