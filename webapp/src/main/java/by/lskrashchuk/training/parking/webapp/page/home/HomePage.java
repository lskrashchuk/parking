package by.lskrashchuk.training.parking.webapp.page.home;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.webapp.app.AuthorizedSession;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;


@AuthorizeInstantiation(value = { "admin", "manager" })
public class HomePage extends AbstractPage{

	@Inject
	private PlaceService placeService;
	
	
	public HomePage() {
        super();
        
        User loggedUser = AuthorizedSession.get().getLoggedUser();
        Roles roles = AuthorizedSession.get().getRoles();
		add(new Label("label-name", AuthorizedSession.get().getLoggedUser().getFirstName()+" "+AuthorizedSession.get().getLoggedUser().getLastName()));
		Integer c = placeService.getAll().size();
		for (Place place : placeService.getAll()) {
			if (placeService.getIsBusy(place)) {
			 c--;
			}
		}

		add(new Label("label-count", c));
		
		
    }

}
