package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.CarPhoto;

public interface CarPhotoDao {
	
	CarPhoto get(Long id);
	
	CarPhoto save();

}
