package by.lskrashchuk.training.parking.webapp.component.login;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.webapp.app.AuthorizedSession;
import by.lskrashchuk.training.parking.webapp.page.login.LoginPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;

public class LoginPanel extends Panel{

	public LoginPanel(String id) {
		super(id);
	}


	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Label("label-name", AuthorizedSession.get().getLoggedUser().getFirstName()+" "+AuthorizedSession.get().getLoggedUser().getLastName()));
		
	    add(new Link("link-logout") {
	            @Override
	            public void onClick() {
	                getSession().invalidate();
	                setResponsePage(LoginPage.class);
	            }
	    });


	}
}
