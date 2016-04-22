package by.lskrashchuk.training.parking.service.impl;

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
		// TODO Auto-generated method stub
		
	}
	
	

}
