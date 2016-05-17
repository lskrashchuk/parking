package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.RegistryDao;
import by.lskrashchuk.training.parking.datamodel.Registry;

@Repository
public class RegistryDaoImpl extends AbstractDaoImpl<Registry, Long> implements RegistryDao{

	protected RegistryDaoImpl() {
		super(Registry.class);
	}

}
