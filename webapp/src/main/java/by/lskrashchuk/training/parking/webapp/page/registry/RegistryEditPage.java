package by.lskrashchuk.training.parking.webapp.page.registry;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class RegistryEditPage extends AbstractPage{


	public RegistryEditPage(PageParameters parameters) {
		super(parameters);
	}
	public RegistryEditPage(Registry registry) {
		super();
	}

}
