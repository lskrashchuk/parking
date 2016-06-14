package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.UserType;

public class PlaceChoiceRenderer extends ChoiceRenderer<Place>{

	
	public static final PlaceChoiceRenderer INSTANCE = new PlaceChoiceRenderer();

		
	public PlaceChoiceRenderer() {
		super();
	}

	
	@Override
	public Object getDisplayValue(Place object) {
		return object.getNumber();
	}

	@Override
	public String getIdValue(Place object, int index) {

		return String.valueOf(object.getId());

	}

	

}

