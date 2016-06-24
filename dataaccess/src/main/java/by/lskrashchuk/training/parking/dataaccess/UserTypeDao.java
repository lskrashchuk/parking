package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.UserTypeFilter;
import by.lskrashchuk.training.parking.datamodel.UserType;

public interface UserTypeDao extends AbstractDao<UserType, Long>{

	   List<UserType> find(UserTypeFilter filter);

	   long count(UserTypeFilter filter);
	    
	   UserType getWithUser(Long id);
 

}
