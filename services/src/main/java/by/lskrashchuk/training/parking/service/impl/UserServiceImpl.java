package by.lskrashchuk.training.parking.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Inject
	private UserDao userDao;

	@Override
	public void register(User user) {
		user.setCreated(new Date());
		userDao.insert(user);
        LOGGER.info("User regirstred: {}", user);
	}

	@Override
	public User getUser(Long id) {
		return userDao.get(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
        LOGGER.info("User updated: {}", user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
        LOGGER.info("User deleted, id: {}", id);
	}

	@Override
	public List<User> find(UserFilter filter) {
		return userDao.find(filter);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	

}
