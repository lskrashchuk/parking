package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.User;

public interface RegistryService {
	
	@Transactional
	void register(Registry registry);
	
	Registry getRegistry(Long id);
	
	@Transactional
	void update(Registry registry);
	
	@Transactional
	void delete(Long id);
	
	Long count(RegistryFilter filter);
	
    List<Registry> find(RegistryFilter filter);



}
