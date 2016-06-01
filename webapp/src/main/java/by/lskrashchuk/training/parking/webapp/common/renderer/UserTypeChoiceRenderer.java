package by.lskrashchuk.training.parking.webapp.common.renderer;


import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.UserType;

public class UserTypeChoiceRenderer extends ChoiceRenderer<UserType>{

	public static final UserTypeChoiceRenderer INSTANCE = new UserTypeChoiceRenderer();

	
	public UserTypeChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(UserType object) {
		return object.getName();
	}

	@Override
	public String getIdValue(UserType object, int index) {
//		return String.valueOf(object.getId());
		return String.valueOf(1);
	}

	

}
