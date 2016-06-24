package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.datamodel.Registry;

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
    
    @Transactional
	void saveOrUpdate(Registry registry);
	



}
