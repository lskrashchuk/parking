package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Brand;

public interface BrandDao {
	
	Brand get(Long id);
	
	Brand save();
	

}
