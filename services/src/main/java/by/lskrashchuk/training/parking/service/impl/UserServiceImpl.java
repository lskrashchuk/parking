package by.lskrashchuk.training.parking.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDao userDao;

	@Override
	public void register(User user) {
		userDao.insert(user);
		user.setCreated(new Date());

	}

	@Override
	public User getUser(Long id) {
		return userDao.get(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}
	
	

}
