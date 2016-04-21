package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Car;

public interface CarDao {
	
	Car get(Long id);
	
	Car save();

}
