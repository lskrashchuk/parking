package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;


import by.lskrashchuk.training.parking.dataaccess.filters.BrandFilter;
import by.lskrashchuk.training.parking.datamodel.Brand;

public interface BrandDao extends AbstractDao<Brand, Long>{
	
	Brand get(Long id);

	List<Brand> find(BrandFilter filter);

	public Brand getWithModels(Long id); 
}
