package by.lskrashchuk.training.parking.webapp.page.user.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.CarService;

public class CarListPanel extends Panel{
	
	@Inject
	private CarService carService;

	public CarListPanel(String id, User user) {
		super(id);
//		List list = Arrays.asList(new String[] { "Volvo FL, 6578 FT-3", "Peugeot 307, 7489 KI-4" });
		List<String> list = new ArrayList<String>();
		for(Car car : user.getCars()){
			list.add(car.getModel().getBrand().getName() +" "+car.getModel().getName()+" "+car.getRegNumber());
		}

		ListView listview = new ListView("carlist", list) {
		    protected void populateItem(ListItem item) {
		        item.add(new Label("car", item.getModel()));
		    }
		};
		add(listview);
	}

}
