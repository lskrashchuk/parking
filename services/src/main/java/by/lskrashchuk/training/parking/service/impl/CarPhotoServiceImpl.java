package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.CarPhotoDao;
import by.lskrashchuk.training.parking.dataaccess.filters.CarPhotoFilter;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.service.CarPhotoService;

@Service
public class CarPhotoServiceImpl implements CarPhotoService{
	
    private static Logger LOGGER = LoggerFactory.getLogger(CarPhotoServiceImpl.class);

	@Inject
	private CarPhotoDao carPhotoDao;
	
	@Override
	public void register(CarPhoto carPhoto) {
		carPhotoDao.insert(carPhoto);
        LOGGER.info("CarPhoto regirstred: {}", carPhoto);
		
	}

	@Override
	public CarPhoto getCarPhoto(Long id) {
		return carPhotoDao.get(id);
	}

	@Override
	public void update(CarPhoto carPhoto) {
		carPhotoDao.update(carPhoto);
		LOGGER.info("CarPhoto updated: {}", carPhoto);
	}

	@Override
	public void delete(Long id) {
		carPhotoDao.delete(id);
        LOGGER.info("CarPhoto deleted, id: {}", id);
		
	}

	@Override
	public List<CarPhoto> find(CarPhotoFilter filter) {
		return carPhotoDao.find(filter);
	}

	@Override
	public List<CarPhoto> getAll() {
		return carPhotoDao.getAll();
	}


}
