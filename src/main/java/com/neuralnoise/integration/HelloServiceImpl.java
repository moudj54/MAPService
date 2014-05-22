package com.neuralnoise.integration;

import org.springframework.stereotype.Service;

@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {
	
	@Override
	public void hello(String name) {
		System.out.println("Hello, " + name);
	}
	
}