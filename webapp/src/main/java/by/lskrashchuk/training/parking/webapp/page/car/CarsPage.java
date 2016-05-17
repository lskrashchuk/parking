package by.lskrashchuk.training.parking.webapp.page.car;

import javax.inject.Inject;

import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class CarsPage extends AbstractPage{
	
	@Inject
	private CarService carService;

	public CarsPage() {
		super();
	}
	
	

}
