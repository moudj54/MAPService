package com.neuralnoise.map.service.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neuralnoise.map.data.map.ArtisanDAO;
import com.neuralnoise.map.model.map.Artisan;
import com.neuralnoise.map.service.map.util.AbstractEntityServiceImpl;

@Service
public class ArtisanServiceImpl extends AbstractEntityServiceImpl<Artisan, ArtisanDAO> implements ArtisanService {

	private static final Logger log = LoggerFactory.getLogger(ArtisanServiceImpl.class);
	
}
