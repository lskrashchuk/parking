package by.lskrashchuk.training.parking.webapp.page.place;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.place.panel.PlaceListPanel;

@AuthorizeInstantiation(value = { "admin", "manager", "guard" })
public class PlacesPage extends AbstractPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlacesPage() {
		super();
        add(new PlaceListPanel("list-panel"));
        
        add(new Link<Void>("create") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
                setResponsePage(new PlaceEditPage(new Place()));
            }
        });
        
        add(new FeedbackPanel("feedback"));
 
	}
}
