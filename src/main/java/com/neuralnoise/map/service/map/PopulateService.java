package com.neuralnoise.map.service.map;

public interface PopulateService {

	public void populate(String path) throws Exception;

	public void clean() throws Exception;

	public void request(String adapterName, String resource);

	public void collect();

}
