package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.PlaceFilter;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Section;
import by.lskrashchuk.training.parking.datamodel.User;

public interface PlaceService {
	
	@Transactional
	void register(Place place);
	
	Place getPlace(Long id);
	
	@Transactional
	void update(Place place);
	
	@Transactional
	void delete(Long id);
	
	 List<Place> getAll();
	 
	 List<Section> getAllSections();
	 
	 List<Place> find(PlaceFilter filter);

	Long count(PlaceFilter filter);


}
