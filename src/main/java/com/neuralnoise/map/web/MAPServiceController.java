package com.neuralnoise.map.web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MAPServiceController {

	private static final String template = "Ciao, %s!";
	private final AtomicLong counter = new AtomicLong();

	public class Message {
		private final long id;
		private final String content;

		public Message(long id, String content) {
			this.id = id;
			this.content = content;
		}

		public long getId() {
			return id;
		}

		public String getContent() {
			return content;
		}
	}

	@RequestMapping("/hello")
	public @ResponseBody
	Message hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		return new Message(counter.incrementAndGet(), String.format(template, name));
	}
	
}
