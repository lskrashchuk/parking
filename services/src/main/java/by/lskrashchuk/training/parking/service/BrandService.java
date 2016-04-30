package by.lskrashchuk.training.parking.service;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.Brand;

public interface BrandService {
	
	@Transactional
	void register(Brand brand);
	
	Brand getBrand(Long id);
	
	@Transactional
	void update(Brand brand);
	
	@Transactional
	void delete(Long id);

}
