package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.datamodel.Registry;

public interface RegistryDao extends AbstractDao<Registry, Long>{

	Long count(RegistryFilter filter);

	List<Registry> find(RegistryFilter filter);

}
