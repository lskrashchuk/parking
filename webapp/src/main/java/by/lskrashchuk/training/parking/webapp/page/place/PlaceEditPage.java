package by.lskrashchuk.training.parking.webapp.page.place;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class PlaceEditPage extends AbstractPage{

	public PlaceEditPage(PageParameters parameters) {
		super(parameters);
	}
	
	public PlaceEditPage(Place place) {
		super();
	}
	

}
