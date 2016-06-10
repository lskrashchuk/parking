package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
    private static Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

	@Inject
	private BrandDao brandDao;
	
	@Override
	public void register(Brand brand) {
		brandDao.insert(brand);
        LOGGER.info("Brand regirstred: {}", brand);
	}

	@Override
	public Brand getBrand(Long id) {
		return brandDao.get(id);
	}

	@Override
	public void update(Brand brand) {
		brandDao.update(brand);
        LOGGER.info("Brand updated: {}", brand);

	}

	@Override
	public void delete(Long id) {
		brandDao.delete(id);
        LOGGER.info("Brand deleted, id: {}", id);

	}

	@Override
	public List<Brand> getAll() {
		return brandDao.getAll();	}
	

}
