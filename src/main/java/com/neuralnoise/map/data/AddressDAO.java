package com.neuralnoise.map.data;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.neuralnoise.map.model.geo.Address;

@Repository
@Transactional
public class AddressDAO extends AbstractDAO<Address, Long> {

	private static final Logger log = LoggerFactory.getLogger(AddressDAO.class);

	public AddressDAO() {
		super(Address.class);
	}

}
