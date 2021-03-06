package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;

public interface UserDao extends AbstractDao<User, Long>{
	
	Long count(UserFilter filter);

	List<User> find(UserFilter filter);
	
	User find(String userName, String password);
	
	User getWithAll(Long id);

	
}
