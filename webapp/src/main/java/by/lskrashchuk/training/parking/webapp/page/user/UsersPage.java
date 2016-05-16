package by.lskrashchuk.training.parking.webapp.page.user;

import javax.inject.Inject;

import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class UsersPage extends AbstractPage{

	@Inject
	private UserService userService;
	
	public UsersPage() {
		super();
	}

}
