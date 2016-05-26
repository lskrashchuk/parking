package by.lskrashchuk.training.parking.webapp.page.place;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = { "admin" })
public class PlacesPage extends AbstractPage{
	
	@Inject
	private PlaceService placeService;

	public PlacesPage() {
		super();
	}
	
	

}
