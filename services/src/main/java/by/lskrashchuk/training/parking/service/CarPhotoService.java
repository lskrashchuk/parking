package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.CarPhotoFilter;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;

public interface CarPhotoService {

	@Transactional
	void register(CarPhoto carPhoto);
	
	CarPhoto getCarPhoto(Long id);
	
	@Transactional
	void update(CarPhoto carPhoto);
	
	@Transactional
	void delete(Long id);
	
    List<CarPhoto> find(CarPhotoFilter filter);

    List<CarPhoto> getAll();

}
