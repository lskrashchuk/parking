package by.lskrashchuk.training.parking.service;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;

public interface RegistryService {
	
	@Transactional
	void register(Registry registry);
	
	Registry getRegistry(Long id);
	
	@Transactional
	void update(Registry registry);
	
	@Transactional
	void delete(Long id);


}
