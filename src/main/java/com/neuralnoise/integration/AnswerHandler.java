package com.neuralnoise.integration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class AnswerHandler {

	@ServiceActivator
	public void handle(Request request) {
		System.out.println("answer: " + request);
	}

}
