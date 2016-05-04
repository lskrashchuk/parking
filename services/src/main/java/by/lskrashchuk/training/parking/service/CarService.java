package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;

public interface CarService {
	
	@Transactional
	void register(Car car);
	
	Car getCar(Long id);
	
	@Transactional
	void update(Car car);
	
	@Transactional
	void delete(Long id);
	
    List<Car> find(CarFilter filter);

    List<Car> getAll();



}
