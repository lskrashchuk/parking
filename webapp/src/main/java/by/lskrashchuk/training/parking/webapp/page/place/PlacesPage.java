package by.lskrashchuk.training.parking.webapp.page.place;

import javax.inject.Inject;

import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class PlacesPage extends AbstractPage{
	
	@Inject
	private PlaceService placeService;

	public PlacesPage() {
		super();
	}
	
	

}
