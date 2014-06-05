package com.neuralnoise.map.service.map;

public interface PopulateService {
	
	public void request(String adapterName, String resource);

	public void collect();

}
