package by.lskrashchuk.training.parking.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.PlaceDao;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService{
    private static Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Inject
    private PlaceDao placeDao;
    
	@Override
	public void register(Place place) {
		placeDao.insert(place);
        LOGGER.info("Place regirstred: {}", place);
	}

	@Override
	public Place getPlace(Long id) {
		return placeDao.get(id);
	}

	@Override
	public void update(Place place) {
		placeDao.update(place);
        LOGGER.info("Place updated: {}", place);
	}

	@Override
	public void delete(Long id) {
		placeDao.delete(id);
        LOGGER.info("Place deleted, id: {}", id);
		
	}

}