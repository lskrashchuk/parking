package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.Role;

public class UserRoleChoiceRenderer extends ChoiceRenderer<Role> {

    public static final UserRoleChoiceRenderer INSTANCE = new UserRoleChoiceRenderer();

    private UserRoleChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(Role object) {
        return object.name();
    }

    @Override
    public String getIdValue(Role object, int index) {
        return String.valueOf(object.ordinal());
    }
}
