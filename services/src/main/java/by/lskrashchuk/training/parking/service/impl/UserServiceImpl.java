package by.lskrashchuk.training.parking.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.dataaccess.UserTypeDao;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private UserDao userDao;

	@Inject
	private UserTypeDao userTypeDao;

	@Override
	public void register(User user) {
//		user.setCreated(new Date());
		userDao.insert(user);
		LOGGER.info("User registred: {}", user);
	}

	@Override
	public User getUser(Long id) {
		return userDao.getWithAll(id);
	}

	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() == null) {
			userDao.insert(user);
			LOGGER.info("User inserted: {}", user);
		} else {
			userDao.update(user);
			LOGGER.info("User updated: {}", user);
		}

	}

	@Override
	public void delete(User user) {
		userDao.delete(user.getId());
		LOGGER.info("User deleted: {}", user);
	}

	@Override
	public List<User> find(UserFilter filter) {
		return userDao.find(filter);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public Long count(UserFilter filter) {
		return userDao.count(filter);

	}

	@Override
	public User getByNameAndPassword(String userName, String password) {
		return userDao.find(userName, password);
	}

	@Override
	public Collection<? extends String> resolveRoles(Long id) {
		User user = userDao.get(id);
		return Collections.singletonList(user.getRole().name());
	}

	@Override
	public List<UserType> getAllUserTypes() {
		return userTypeDao.getAll();
	}

}
