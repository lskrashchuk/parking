package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Color;

public class CarColorChoiceRenderer extends ChoiceRenderer<Color>{
	public static final CarColorChoiceRenderer INSTANCE = new CarColorChoiceRenderer();

	
	public CarColorChoiceRenderer() {
		super();
	}

	
	@Override
	public Object getDisplayValue(Color object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Color object, int index) {

		return String.valueOf(object.getId());

	}

	


}


