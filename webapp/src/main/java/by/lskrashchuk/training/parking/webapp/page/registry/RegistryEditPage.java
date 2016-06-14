package by.lskrashchuk.training.parking.webapp.page.registry;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.util.lang.Bytes;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Role;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.service.RegistryService;
import by.lskrashchuk.training.parking.service.UserTypeService;
import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.PlaceChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.RegistryTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserRoleChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;
import by.lskrashchuk.training.parking.webapp.page.user.panel.UserCarListPanel;

public class RegistryEditPage extends AbstractPage{

	@Inject
	private RegistryService registryService;

	@Inject
	private CarService carService;

	@Inject
	private PlaceService placeService;

	private Registry registry;

	public RegistryEditPage(PageParameters parameters) {
		super(parameters);
	}
	public RegistryEditPage(Registry registry) {
		super();
		if (registry.getId() == null) {
			this.registry = registry;
		} else {
			this.registry = registryService.getRegistry(registry.getId());
		}
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form");
		add(form);

		Form<Registry> textForm = new Form<Registry>("textForm", new CompoundPropertyModel<Registry>(registry));

		form.add(textForm);
		
		DateTextField eventtimeField = new DateTextField("eventTime", "dd-MM-yyyy hh:mm");
		eventtimeField.add(new DatePicker());
        eventtimeField.setLabel(new ResourceModel("registry.eventTime"));
		eventtimeField.setRequired(true);
		textForm.add(eventtimeField);

		DropDownChoice<EventType> eventTypeField = new DropDownChoice<>("eventType", Arrays.asList(EventType.values()),
				RegistryTypeChoiceRenderer.INSTANCE);
        eventTypeField.setLabel(new ResourceModel("registry.eventType"));
		eventTypeField.setRequired(true);
		textForm.add(eventTypeField);
		
		DropDownChoice<Car> carField = new DropDownChoice<>("car", carService.getAll(),
				CarChoiceRenderer.INSTANCE);
        carField.setLabel(new ResourceModel("registry.car"));
		carField.setRequired(true);
		textForm.add(carField);

		DropDownChoice<Place> placeField = new DropDownChoice<>("place", placeService.getAll(),
				PlaceChoiceRenderer.INSTANCE);
        placeField.setLabel(new ResourceModel("registry.place"));
		placeField.setRequired(true);
		textForm.add(placeField);

		textForm.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();

				registryService.saveOrUpdate(registry);
				RegistryPage page = new RegistryPage();
				String localizedMessage = getString("registry.saved");
                page.info(localizedMessage);
				setResponsePage(page);
			}
		});

		FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
		feedback.setOutputMarkupId(true);
		

	}


}
