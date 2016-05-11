package by.lskrashchuk.training.parking.service;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.dataaccess.impl.AbstractDaoImpl;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;
import by.lskrashchuk.training.parking.datamodel.Role;
import by.lskrashchuk.training.parking.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-test.xml"})
public class UserServiceTest {
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserDao userDao;
	
	@Test
	public void test() {
		
		Assert.assertNotNull(userService);
				
	}
	
	@Test
	public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(userDao);
		
		Assert.assertNotNull(em);
		
	}
	
	@Test
	public void testRegistrationUser(){
		User user = registrationUser("testFName", "testLName");
		
        User registredUser = userService.getUser(user.getId());

        Assert.assertNotNull(registredUser);


        String updatedFName = "updatedName";
        user.setFirstName(updatedFName);
        userService.update(user);

        Assert.assertEquals(updatedFName, userService.getUser(user.getId()).getFirstName());

        deleteUser(user);
  
        Assert.assertNull(userService.getUser(user.getId()));
	}

	
	private void deleteUser(User user) {

		userService.delete(user.getId());

	}


	private User registrationUser(String fn, String ln) {
		User user = new User();
		user.setFirstName(fn);
		user.setLastName(ln);
//		byte[] b = "hello there".getBytes();
		byte[] b = null;
		user.setPhoto(b);
		user.setPhone("+375777777");
        user.setEmail(System.currentTimeMillis() + "mail@test.by");
        user.setPassword("pswd");
        user.setUserType(null);
        user.setRole(Role.admin);
        userService.register(user);
        
 		return user;
	}
	
	   @Test
	    public void testSearch() {
	        // clean all data from users
	        List<User> all = userService.getAll();
	        for (User user : all) {
	            userService.delete(user.getId());
	        }

	        // start create new data
	        int testObjectsCount = 5;
	        for (int i = 0; i < testObjectsCount; i++) {
	    		User user = registrationUser("testFName"+i, "testLName"+i);
	        }

	        UserFilter filter = new UserFilter();
	        List<User> result = userService.find(filter);
	        Assert.assertEquals(testObjectsCount, result.size());
	        // test paging
	        filter.setFetchCredentials(true);
	        int limit = 3;
	        filter.setLimit(limit);
	        filter.setOffset(0);
	        result = userService.find(filter);
	        Assert.assertEquals(limit, result.size());

	        // test sort

	        filter.setLimit(null);
	        filter.setOffset(null);
	        filter.setSortOrder(true);
	        filter.setSortProperty(User_.firstName);
	        result = userService.find(filter);
	        Assert.assertEquals(testObjectsCount, result.size());

	    }


}
