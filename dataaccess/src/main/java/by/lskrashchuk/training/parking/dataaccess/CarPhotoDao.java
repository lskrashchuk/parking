package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.CarPhotoFilter;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;

public interface CarPhotoDao extends AbstractDao<CarPhoto, Long>{

	CarPhoto get(Long id);
	
	List<CarPhoto> find(CarPhotoFilter filter);
}
