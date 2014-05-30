package com.neuralnoise.integration;

import org.springframework.integration.annotation.Gateway;

public interface RequestsGateway {

	@Gateway
	public void sendMessage(Object o);

}
