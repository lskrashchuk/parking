package by.lskrashchuk.training.parking.service;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.datamodel.User;

public interface ColorService {

	@Transactional
	void register(Color color);
	
	Color getColor(Long id);
	
	@Transactional
	void update(Color color);
	
	@Transactional
	void delete(Long id);
	
	@Transactional
	void saveOrUpdate(Color color);
	
	Color getByName(String colorName);


}
