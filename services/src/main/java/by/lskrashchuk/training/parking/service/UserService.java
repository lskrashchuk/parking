package by.lskrashchuk.training.parking.service;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.datamodel.User;

public interface UserService {
	
	@Transactional
	void register(User user);
	
	User getUser(Long id);
	
	@Transactional
	void update(User user);
	
	@Transactional
	void delete(Long id);

}
