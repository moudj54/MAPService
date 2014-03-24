package com.neuralnoise.map.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CrashController {

	@RequestMapping(value = "/whoops", method = RequestMethod.GET)
	public String triggerException() {
		throw new RuntimeException("Exception expected: controller used to showcase what happens when an exception is thrown");
	}

}
