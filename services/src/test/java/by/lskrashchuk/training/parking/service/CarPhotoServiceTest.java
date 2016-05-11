package by.lskrashchuk.training.parking.service;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.dataaccess.CarDao;
import by.lskrashchuk.training.parking.dataaccess.CarPhotoDao;
import by.lskrashchuk.training.parking.dataaccess.impl.AbstractDaoImpl;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-test.xml"})
public class CarPhotoServiceTest {
	
	@Inject
	private CarService carService;

	@Inject
	private CarDao carDao;
	
	@Inject
	private CarPhotoService carPhotoService;

	@Inject
	private CarPhotoDao carPhotoDao;

	@Test
	public void test() {
		
		Assert.assertNotNull(carPhotoService);
				
	}
	
	@Test
	public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(carPhotoDao);
		
		Assert.assertNotNull(em);
		
	}

	
	@Test
	public void testRegistrationCar(){
		CarPhoto carPhoto = new CarPhoto();
		carPhoto.setCar(carDao.get(new Long(2)));
		byte[] b = "hello there".getBytes();
		carPhoto.setPhoto(b);
		carPhotoService.register(carPhoto);
		

		CarPhoto registredCarPhoto = carPhotoService.getCarPhoto(carPhoto.getId());

		Assert.assertNotNull(registredCarPhoto);

	}

}
