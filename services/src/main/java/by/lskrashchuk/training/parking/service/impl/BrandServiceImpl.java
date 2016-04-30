package by.lskrashchuk.training.parking.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Inject
	private BrandDao brandDao;
	
	@Override
	public void register(Brand brand) {
		brandDao.insert(brand);
	}

	@Override
	public Brand getBrand(Long id) {
		return brandDao.get(id);
	}

	@Override
	public void update(Brand brand) {
		brandDao.update(brand);
	}

	@Override
	public void delete(Long id) {
		brandDao.delete(id);
	}
	

}
