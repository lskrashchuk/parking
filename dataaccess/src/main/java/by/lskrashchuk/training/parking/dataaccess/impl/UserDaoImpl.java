package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.datamodel.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao{

	protected UserDaoImpl() {
		super(User.class);
	}

	

}
