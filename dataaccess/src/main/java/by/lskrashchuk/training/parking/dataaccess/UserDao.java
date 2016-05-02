package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;

public interface UserDao extends AbstractDao<User, Long>{
	
    List<User> find(UserFilter filter);

}
