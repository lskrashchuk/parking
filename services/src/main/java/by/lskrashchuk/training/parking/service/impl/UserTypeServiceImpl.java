package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.UserTypeDao;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService{
	
	@Inject
	private UserTypeDao userTypeDao;

	@Override
	public List<UserType> getAll() {
		return userTypeDao.getAll();
	}

}
