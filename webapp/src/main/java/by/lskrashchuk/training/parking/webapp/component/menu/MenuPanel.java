package by.lskrashchuk.training.parking.webapp.component.menu;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.webapp.page.car.CarsPage;
import by.lskrashchuk.training.parking.webapp.page.login.LoginPage;
import by.lskrashchuk.training.parking.webapp.page.place.PlacesPage;
import by.lskrashchuk.training.parking.webapp.page.registry.RegistryPage;
import by.lskrashchuk.training.parking.webapp.page.report.ReportsPage;
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

        add(new Link("link-places") {
            @Override
            public void onClick() {
                setResponsePage(new PlacesPage());
            }
        }); 

        add(new Link("link-cars") {
            @Override
            public void onClick() {
                setResponsePage(new CarsPage());
            }
        }); 

        add(new Link("link-registry") {
            @Override
            public void onClick() {
                setResponsePage(new RegistryPage());
            }
        }); 
 
        add(new Link("link-reports") {
            @Override
            public void onClick() {
                setResponsePage(new ReportsPage());
            }
        }); 
        
        add(new Link("link-logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        });


	}

}
