package com.neuralnoise.integration;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import com.neuralnoise.integration.util.CAnswer;

@Component
public class AnswerHandler {

	@ServiceActivator
	public void handle(CAnswer answer) {
		System.out.println("answer: " + answer);
	}

}
