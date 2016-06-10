package by.lskrashchuk.training.parking.webapp.page.car;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.car.panel.CarListPanel;

public class CarsPage extends AbstractPage{
	
	@Inject
	private CarService carService;

	public CarsPage() {
		super();
        add(new CarListPanel("list-panel"));
        
        add(new Link("create") {
            @Override
            public void onClick() {
                setResponsePage(new CarEditPage(new Car()));
            }
        });

	}
	
	

}
