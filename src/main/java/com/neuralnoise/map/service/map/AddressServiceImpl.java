package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.AddressDAO;
import com.neuralnoise.map.model.geo.Address;
import com.neuralnoise.map.service.map.util.AbstractEntityServiceImpl;

@Service
public class AddressServiceImpl extends AbstractEntityServiceImpl<Address, AddressDAO> implements AddressService {

	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
	
}
