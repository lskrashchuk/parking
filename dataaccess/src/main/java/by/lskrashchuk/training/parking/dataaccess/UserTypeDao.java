package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.UserType;

public interface UserTypeDao {
	
	UserType get(Long id);
	
	UserType save();

}
