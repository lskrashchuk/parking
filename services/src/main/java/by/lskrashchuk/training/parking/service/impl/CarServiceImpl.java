package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.CarDao;
import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.service.CarService;

@Service
public class CarServiceImpl implements CarService{
    private static Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

	@Inject
	private CarDao carDao;

    @Override
	public void register(Car car) {
		carDao.insert(car);
        LOGGER.info("Car regirstred: {}", car);
	}

	@Override
	public Car getCar(Long id) {
		return carDao.get(id);
	}

	@Override
	public void update(Car car) {
		carDao.update(car);
		LOGGER.info("Car updated: {}", car);
	}

	@Override
	public void delete(Long id) {
		carDao.delete(id);
        LOGGER.info("Car deleted, id: {}", id);

	}

	@Override
	public List<Car> find(CarFilter filter) {
		return carDao.find(filter);
	}

	@Override
	public List<Car> getAll() {
		return carDao.getAll();
	}


}
