package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.RegistryDao;
import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.service.RegistryService;

@Service
public class RegistryServiceImpl implements RegistryService{
    private static Logger LOGGER = LoggerFactory.getLogger(RegistryServiceImpl.class);

	@Inject
	private RegistryDao registryDao;
	
    @Override
	public void register(Registry registry) {
		registryDao.insert(registry);
        LOGGER.info("Registry regirstred: {}", registry);
		
	}

	@Override
	public Registry getRegistry(Long id) {
		return registryDao.get(id);
	}

	@Override
	public void update(Registry registry) {
		registryDao.update(registry);
        LOGGER.info("Registry updated: {}", registry);
		
	}

	@Override
	public void delete(Long id) {
		registryDao.delete(id);
        LOGGER.info("Registry deleted, id: {}", id);
		
	}

	@Override
	public Long count(RegistryFilter filter) {
        return registryDao.count(filter);
	}

	@Override
	public List<Registry> find(RegistryFilter filter) {
		return registryDao.find(filter);
	}


}
