package com.neuralnoise.integration;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 5019596129437896095L;

	private String header;
	private String body;
	
	public Request() { }
	
	public Request(String header, String body) {
		this.setHeader(header);
		this.setBody(body);
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Request [header=" + header + ", body=" + body + "]";
	}
	
}
