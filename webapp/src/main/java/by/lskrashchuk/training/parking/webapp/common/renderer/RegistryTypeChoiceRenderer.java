package by.lskrashchuk.training.parking.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Role;

public class RegistryTypeChoiceRenderer extends ChoiceRenderer<EventType> {

    public static final RegistryTypeChoiceRenderer INSTANCE = new RegistryTypeChoiceRenderer();

    private RegistryTypeChoiceRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(EventType object) {
        return object.name();
    }

    @Override
    public String getIdValue(EventType object, int index) {
        return String.valueOf(object.ordinal());
    }
}
