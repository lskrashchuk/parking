package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Place;

public interface PlaceDao {
	
	Place get(Long id);
	
	Place save();

}
