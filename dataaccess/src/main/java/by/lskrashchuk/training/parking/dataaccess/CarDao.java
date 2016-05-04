package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;

public interface CarDao extends AbstractDao<Car, Long>{
	
	Car get(Long id);
	
	List<Car> find(CarFilter filter);

}
