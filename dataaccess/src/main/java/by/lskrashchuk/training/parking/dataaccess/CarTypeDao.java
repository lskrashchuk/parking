package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.CarType;

public interface CarTypeDao {
	
	CarType get(Long id);
	
	CarType save();

}
