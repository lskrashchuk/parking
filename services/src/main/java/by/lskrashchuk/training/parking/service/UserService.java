package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;

public interface UserService {
	
	@Transactional
	void register(User user);
	
	User getUser(Long id);
	
	@Transactional
	void update(User user);
	
	@Transactional
	void delete(Long id);
	
    List<User> find(UserFilter filter);

    List<User> getAll();


}
