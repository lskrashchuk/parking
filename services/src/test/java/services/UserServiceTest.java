package services;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Blob;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.dataaccess.UserDao;
import by.lskrashchuk.training.parking.dataaccess.impl.AbstractDaoImpl;
import by.lskrashchuk.training.parking.datamodel.User;
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
	public void testRegistration(){
		User user = new User();
		user.setFirstName("testFName");
		user.setLastName("testLName");
		byte[] b = "hello there".getBytes();
		user.setPhoto(b);
		user.setPhone("+375777777");
        user.setEmail(System.currentTimeMillis() + "mail@test.by");
        user.setPassword("pswd");
        user.setUserType(null);
        user.setRole(Role.admin);
        userService.register(user);
        
 //       Long userId = user.getId();

        User registredUser = userService.getUser(user.getId());

        Assert.assertNotNull(registredUser);

        String updatedFName = "updatedName";
        user.setFirstName(updatedFName);
        userService.update(user);

        Assert.assertEquals(updatedFName, userService.getUser(user.getId()).getFirstName());

        userService.delete(user.getId());

        Assert.assertNull(userService.getUser(user.getId()));
  
	}
	
	

}
