package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Place;

public interface CarDao extends AbstractDao<Car, Long>{
	
	Car get(Long id);
	
	List<Car> find(CarFilter filter);
	
    Long count(CarFilter filter);

	Car getWithAll(Long id);
	
	Long getPlaceId(Car car);
}
