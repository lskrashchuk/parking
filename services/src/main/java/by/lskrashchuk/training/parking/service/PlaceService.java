package by.lskrashchuk.training.parking.service;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.Place;

public interface PlaceService {
	
	@Transactional
	void register(Place place);
	
	Place getPlace(Long id);
	
	@Transactional
	void update(Place place);
	
	@Transactional
	void delete(Long id);


}
