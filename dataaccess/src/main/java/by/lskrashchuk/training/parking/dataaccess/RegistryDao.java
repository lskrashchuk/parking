package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Registry;

public interface RegistryDao {
	
	Registry get(Long id);
	
	Registry save();

}
