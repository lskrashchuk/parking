package by.lskrashchuk.training.parking.webapp.page.user.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.webapp.page.car.CarEditPage;
import by.lskrashchuk.training.parking.webapp.page.registry.RegistryEditPage;

public class UserCarListPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CarService carService;

	public UserCarListPanel(String id, User user) {
		super(id);
		// List list = Arrays.asList(new String[] { "Volvo FL, 6578 FT-3",
		// "Peugeot 307, 7489 KI-4" });
		/*List<String> list = new ArrayList<String>();
		if (user.getCars() != null) {
			for (Car car : user.getCars()) {
				list.add(car.getModel().getBrand().getName() + " " + car.getModel().getName() + " "
						+ car.getRegNumber());
			}
		}

		ListView listview = new ListView("carlist", list) {
			protected void populateItem(ListItem item) {
				item.add(new Label("car", item.getModel()));
			}
		};
		add(listview);*/

	
		List<Car> list = new ArrayList<Car>();
		if (user.getCars() != null) {
			for (Car car : user.getCars()) {
				list.add(car);
			}
		}

		ListView listview = new ListView("carlist", list) {
			protected void populateItem(ListItem item) {
				Link<Void> carLink = new Link<Void>("car-link"){

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new CarEditPage(list.get(item.getIndex())));
					}
				};	
				item.add(carLink);

				carLink.add(new Label("car", list.get(item.getIndex()).getModel().getBrand().getName() + " " + list.get(item.getIndex()).getModel().getName() + " "
						+ list.get(item.getIndex()).getRegNumber()));

			}
		};
		add(listview);

	
	
	}

}
