package by.lskrashchuk.training.parking.webapp.page.place;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Section;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.PlaceChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.RegistryTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.registry.RegistryPage;

public class PlaceEditPage extends AbstractPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PlaceService placeService;


	private Place place;

	
	public PlaceEditPage(PageParameters parameters) {
		super(parameters);
	}
	
	public PlaceEditPage(Place place) {
		super();
		if (place.getId() == null) {
			this.place = place;
		} else {
			this.place = placeService.getPlace(place.getId());
		}
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form form = new Form("form");
		add(form);

		Form<Place> textForm = new Form<Place>("textForm", new CompoundPropertyModel<Place>(place));

		form.add(textForm);
		
		TextField numberField = new TextField("number");
        numberField.setLabel(new ResourceModel("place.number"));
		numberField.setRequired(true);
		textForm.add(numberField);


		textForm.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				placeService.saveOrUpdate(place);
				PlacesPage page = new PlacesPage();
				String localizedMessage = getString("place.saved");
                page.info(localizedMessage);
				setResponsePage(page);
			}
		});

		FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
		feedback.setOutputMarkupId(true);
		

	}
	

}
