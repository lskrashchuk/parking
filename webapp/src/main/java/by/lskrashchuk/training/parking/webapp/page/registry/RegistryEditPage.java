package by.lskrashchuk.training.parking.webapp.page.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.service.RegistryService;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.PlaceChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.RegistryTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class RegistryEditPage extends AbstractPage {

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
			registry.setEventTime(new Date());
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
		eventTypeField.setOutputMarkupId(true);
		textForm.add(eventTypeField);

		IModel carChoiceModel = new AbstractReadOnlyModel() {

			@Override
			public Object getObject() {
				if (registry.getEventType() == null) {
					return Collections.EMPTY_LIST;
				} else {
					List<Car> result = new ArrayList<Car>();
					if (registry.getEventType() == EventType.departed) {
							for (Place place : placeService.getAll()) {
								if (placeService.getIsBusy(place)) {
									result.add(carService.getCar(placeService.getCarId(place)));
								}
						}
					} else {
						result = carService.getAll();
						for (Place place : placeService.getAll()) {
							if (placeService.getIsBusy(place)) {
								result.remove(carService.getCar(placeService.getCarId(place)));
							}
						}
					}
					return result;

				}
			}
		};

		DropDownChoice<Car> carField = new DropDownChoice<>("car", carChoiceModel, CarChoiceRenderer.INSTANCE);
		carField.setLabel(new ResourceModel("registry.car"));
		carField.setRequired(true);
		carField.setOutputMarkupId(true);
		textForm.add(carField);

		IModel placeChoiceModel = new AbstractReadOnlyModel() {

			@Override
			public Object getObject() {
				List<Place> result = new ArrayList<Place>();
				if (registry.getEventType() != null) {
					if (registry.getEventType()==EventType.arrived) {
						result = placeService.getAll();
						for (Place place : placeService.getAll()) {
							if (placeService.getIsBusy(place)) {
								result.remove(place);
							}
						}
					} else {
						if (registry.getCar() == null) {
							result = placeService.getAll();
							for (Place place : placeService.getAll()) {
								if (!placeService.getIsBusy(place)) {
									result.remove(place);
								}
							}
						} else {
						Place buzyPlace = placeService.getPlace(carService.getPlaceId(registry.getCar()));
						result.add(buzyPlace);
						registry.setPlace(buzyPlace);
						}
					}
				}
				return result;
			}
		};

		DropDownChoice<Place> placeField = new DropDownChoice<>("place", placeChoiceModel,
				PlaceChoiceRenderer.INSTANCE);
		placeField.setLabel(new ResourceModel("registry.place"));
		placeField.setRequired(true);
		placeField.setOutputMarkupId(true);
		textForm.add(placeField);


		eventTypeField.add(new AjaxFormComponentUpdatingBehavior("change") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
				registry.setPlace(null);
				registry.setCar(null);
				carField.onSelectionChanged();
				placeField.onSelectionChanged();
				target.add(carField);
				target.add(placeField);
			}
		});

		carField.add(new AjaxFormComponentUpdatingBehavior("change") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
				target.add(placeField);
			}
		});

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
