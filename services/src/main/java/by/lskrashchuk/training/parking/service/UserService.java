package by.lskrashchuk.training.parking.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;

public interface UserService {
	
	@Transactional
	void register(User user);
	
	User getUser(Long id);
	
	@Transactional
	void saveOrUpdate(User user);
	
	@Transactional
	void delete(User user);
	
    List<User> find(UserFilter filter);

    List<User> getAll();
    
    List<UserType> getAllUserTypes();

    
    Long count(UserFilter filter);
    
    User getByNameAndPassword(String userName, String password);
    
    Collection<? extends String> resolveRoles(Long id);

    


}
