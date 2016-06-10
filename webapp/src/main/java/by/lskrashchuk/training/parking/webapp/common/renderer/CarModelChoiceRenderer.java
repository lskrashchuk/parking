package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.datamodel.UserType;

public class CarModelChoiceRenderer extends ChoiceRenderer<Model>{
	
	public static final UserTypeChoiceRenderer INSTANCE = new UserTypeChoiceRenderer();

	
	public CarModelChoiceRenderer() {
		super();
	}

	
	@Override
	public Object getDisplayValue(Model object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Model object, int index) {

		return String.valueOf(object.getId());

	}

	


}
