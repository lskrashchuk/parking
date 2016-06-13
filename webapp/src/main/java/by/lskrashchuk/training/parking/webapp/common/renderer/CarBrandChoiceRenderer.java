package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Brand;

public class CarBrandChoiceRenderer extends ChoiceRenderer<Brand>{
	
	public static final CarBrandChoiceRenderer INSTANCE = new CarBrandChoiceRenderer();

	
	public CarBrandChoiceRenderer() {
		super();
	}

	
	@Override
	public Object getDisplayValue(Brand object) {
		return object.getName();
	}

	@Override
	public String getIdValue(Brand object, int index) {

		return String.valueOf(object.getId());

	}

	


}
