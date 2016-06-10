package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.UserType;

public interface BrandService {
	
	@Transactional
	void register(Brand brand);
	
	Brand getBrand(Long id);
	
	@Transactional
	void update(Brand brand);
	
	@Transactional
	void delete(Long id);
	
	List<Brand> getAll();

}
