package by.lskrashchuk.training.parking.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;

public class MenuPanel extends Panel{

	public MenuPanel(String id) {
		super(id);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

        add(new Link("link-users") {
            @Override
            public void onClick() {
                setResponsePage(new UsersPage());
            }
        });

    /*    add(new Link("link-places") {
            @Override
            public void onClick() {
                setResponsePage(new PlacesPage());
            }
        }); */

	
	}

}
