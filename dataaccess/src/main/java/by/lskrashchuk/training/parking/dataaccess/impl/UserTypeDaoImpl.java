package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.UserTypeDao;
import by.lskrashchuk.training.parking.datamodel.UserType;

@Repository
public class UserTypeDaoImpl extends AbstractDaoImpl<UserType, Long> implements UserTypeDao{

	protected UserTypeDaoImpl() {
		super(UserType.class);
	}

}
