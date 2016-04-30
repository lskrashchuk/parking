package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.BrandDao;
import by.lskrashchuk.training.parking.datamodel.Brand;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<Brand, Long> implements BrandDao{
	
	protected BrandDaoImpl(){
		super(Brand.class);
	}

}
