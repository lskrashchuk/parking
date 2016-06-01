package by.lskrashchuk.training.parking.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.webapp.app.AuthorizedSession;
import by.lskrashchuk.training.parking.webapp.component.localization.LanguageSelectionComponent;
import by.lskrashchuk.training.parking.webapp.component.login.LoginPanel;
import by.lskrashchuk.training.parking.webapp.component.menu.MenuPanel;
import by.lskrashchuk.training.parking.webapp.page.home.HomePage;


public abstract class AbstractPage extends WebPage {

	public AbstractPage() {
		super();
	}

	public AbstractPage(PageParameters parameters) {
		super(parameters);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("link-home1") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });
	       
		add(new Link("link-home2") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

		add(new LoginPanel("login-panel"));
        add(new LanguageSelectionComponent("language-select"));

		add(new MenuPanel("menu-panel"));
	}
	

}
