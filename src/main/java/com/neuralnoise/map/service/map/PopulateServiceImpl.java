package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.neuralnoise.integration.RequestsGateway;
import com.neuralnoise.integration.util.CRequest;

@Service
public class PopulateServiceImpl implements PopulateService, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(PopulateServiceImpl.class);

	private ApplicationContext applicationContext;

	@Override
	public void request(String adapterName, String resource) {
		RequestsGateway gateway = (RequestsGateway) applicationContext.getBean("requestsGateway");
		CRequest request = new CRequest();
		request.setAdapterName(adapterName);
		request.setResource(resource);
		log.info("Sending request: " + request);
		gateway.sendMessage(request);
	}

	/*
	 * every day at 10 AM, from http://www.cronmaker.com/
	 */
	@Override
	@Scheduled(cron = "0 0 10 1/1 * ? *")
	public void collect() {
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/4");
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/7");
		request("PugliaEvents", "http://www.pugliaevents.it/it/feeds/category/9");
		//request("Test", "Test resource");
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
