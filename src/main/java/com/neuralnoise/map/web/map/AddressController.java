package com.neuralnoise.map.web.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neuralnoise.map.model.geo.Address;
import com.neuralnoise.map.service.map.AddressService;
import com.neuralnoise.map.web.map.util.AbstractEntityController;

@Controller
@RequestMapping("/address")
public class AddressController extends AbstractEntityController<Address, AddressService> {

	private static final Logger log = LoggerFactory.getLogger(AddressController.class);
	
}
