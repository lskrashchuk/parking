package by.lskrashchuk.training.parking.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.lskrashchuk.training.parking.dataaccess.CarDao;
import by.lskrashchuk.training.parking.dataaccess.impl.AbstractDaoImpl;
import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.datamodel.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-context-test.xml"})
public class CarServiceTest {

	@Inject
	private ColorService colorService;

	@Inject
	private CarService carService;

	@Inject
	private CarDao carDao;

	@Inject
	private ModelService modelService;
	
	@Inject
	private BrandService brandService;


	@Test
	public void test() {
		
		Assert.assertNotNull(carService);
				
	}
	
	@Test
	public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
		f.setAccessible(true);
		EntityManager em = (EntityManager) f.get(carDao);
		
		Assert.assertNotNull(em);
		
	}

	
	@Test
	public void testRegistrationCar(){
		Color color = new Color();
		color.setName("red");
		colorService.register(color);
		
		Brand brand = new Brand();
		brand.setName("Peugeot");
		brandService.register(brand);
		
		Model model = new Model();
		model.setName("307");
		model.setLength(new BigDecimal(2.5));
		model.setWidth(new BigDecimal(1.5));
		model.setBrand(brand);
		model.setCarType(null);
		modelService.register(model);

		Car car = new Car();
		car.setColor(color);
		car.setRegNumber("6574 yh-3");
		car.setYearProduced(1990);
		car.setModel(model);
		car.setUsers(null);
		carService.register(car);

		Car registredCar = carService.getCar(car.getId());

		Assert.assertNotNull(registredCar);

	}




}
