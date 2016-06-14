package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.UserType;

public class CarChoiceRenderer extends ChoiceRenderer<Car>{

	
	public static final CarChoiceRenderer INSTANCE = new CarChoiceRenderer();

		
	public CarChoiceRenderer() {
		super();
	}

	
	@Override
	public Object getDisplayValue(Car object) {
		return object.getBrand().getName()+" "+object.getModel().getName()+" "+object.getRegNumber();
	}

	@Override
	public String getIdValue(Car object, int index) {

		return String.valueOf(object.getId());

	}

	

}
