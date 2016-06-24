package by.lskrashchuk.training.parking.service;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.UserTypeFilter;
import by.lskrashchuk.training.parking.datamodel.UserType;



public interface UserTypeService{
    Long count(UserTypeFilter filter);

    List<UserType> find(UserTypeFilter filter);


	List<UserType> getAll();
	
	UserType getUserType(Long id);

}
