package by.lskrashchuk.training.parking.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.webapp.component.menu.MenuPanel;


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
        add(new MenuPanel("menu-panel"));
	}
	

}