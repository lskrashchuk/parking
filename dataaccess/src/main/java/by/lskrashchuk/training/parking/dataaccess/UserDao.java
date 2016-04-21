package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.User;

public interface UserDao {
	
	User get(Long id);
	
	User save();
	
}
