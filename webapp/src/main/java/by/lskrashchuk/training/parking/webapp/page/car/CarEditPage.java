package by.lskrashchuk.training.parking.webapp.page.car;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.RangeValidator;

import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.service.BrandService;
import by.lskrashchuk.training.parking.service.CarPhotoService;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.ModelService;
import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarBrandChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarColorChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarModelChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.car.panel.CarPhotoListPanel;

public class CarEditPage extends AbstractPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CarService carService;

	@Inject
	private BrandService brandService;

	@Inject
	private ModelService modelService;

	@Inject
	private CarPhotoService carPhotoService;

	private Car car;

	private Boolean ifPhotoUpload;

	public CarEditPage(PageParameters parameters) {
		super();
	}

	public CarEditPage(Car car) {
		super();
		if (car.getId() == null) {
			this.car = car;
		} else {
			this.car = carService.getCar(car.getId());
		}

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		ifPhotoUpload = false;
		
		Form form = new Form("form");
		add(form);

		CompoundPropertyModel<Car> baseModel = new CompoundPropertyModel<Car>(car);

		Form<Car> textForm = new Form<Car>("textForm", baseModel);

		form.add(textForm);

		TextField<String> regnumberField = new TextField<>("regNumber");
		regnumberField.setLabel(new ResourceModel("car.regnumber"));
		regnumberField.setRequired(true);
		textForm.add(regnumberField);

		TextField<Integer> yearproducedField = new TextField<Integer>("yearProduced");
		Calendar calendar = Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
		calendar.setTime(new Date());
		int currentYear = calendar.get(java.util.Calendar.YEAR);
		yearproducedField.add(RangeValidator.<Integer> range(1900, currentYear));
		yearproducedField.setLabel(new ResourceModel("car.yearproduced"));
		yearproducedField.setRequired(true);
		textForm.add(yearproducedField);
		
		List<Color> lc = carService.getAllColors();
		DropDownChoice<Color> colorField = new DropDownChoice<>("color", carService.getAllColors(),
				CarColorChoiceRenderer.INSTANCE);
		colorField.setLabel(new ResourceModel("car.color"));
		colorField.setRequired(true);
		textForm.add(colorField);


		IModel modelChoiceModel = new AbstractReadOnlyModel() {

			@Override
			public Object getObject() {
				if (car.getBrand() == null) {
					return Collections.EMPTY_LIST;
				}
				List<String> result = new ArrayList<String>();
				for (Model model : carService.getAllModels(brandService.getBrand(car.getBrand().getId()))) {
					result.add(model.getName());
				}
				return result;
			}
		};

		DropDownChoice<Brand> brandField = new DropDownChoice<>("brand", carService.getAllBrands(),
				CarBrandChoiceRenderer.INSTANCE);
		brandField.setLabel(new ResourceModel("car.brand"));
		brandField.setRequired(true);
		brandField.setOutputMarkupId(true);
		textForm.add(brandField);

		// DropDownChoice<Model> modelField = new DropDownChoice<>("model",
		// carService.getAllModels(brandService.getBrand(car.getBrand().getId())
		// ), CarModelChoiceRenderer.INSTANCE);
		DropDownChoice modelField = new DropDownChoice("model", baseModel, modelChoiceModel);
		modelField.setLabel(new ResourceModel("car.model"));
		modelField.setRequired(true);
		modelField.setOutputMarkupId(true);
		textForm.add(modelField);

		brandField.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(modelField);
			}
		});



		textForm.add(new SubmitLink("saveCar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				// Integer brandId = Integer.parseInt(brandField.getId());
				String modelString = modelField.getModelObject().toString();
				Model model = modelService.getByName(modelString);
//				Color color = colorField.getModelObject();
				car.setModel(model);
//				car.setColor(color);
				carService.saveOrUpdate(car);
				if (ifPhotoUpload) {
					if (car.getCarPhotos() != null) {
						if (car.getCarPhotos().size() == 0) {
							carPhotoService.register(car.getCarPhotos().get(car.getCarPhotos().size() - 1));
						} else {
							carPhotoService.update(car.getCarPhotos().get(car.getCarPhotos().size() - 1));
						}
					}
					ifPhotoUpload = false;
				}

				CarsPage page = new CarsPage();
				String localizedMessage = getString("car.saved");
				page.info(localizedMessage);
				setResponsePage(page);
			}
		});

		Form<Car> imageForm = new Form<Car>("imageForm", new PropertyModel<Car>(car, "photo"));
		imageForm.setOutputMarkupId(true);

		// Enable multipart mode (need for uploads file)
		imageForm.setMultiPart(true);

		// max upload size, 100k
		imageForm.setMaxSize(Bytes.kilobytes(100));

		Image nullImg = new Image("null_photo", new ContextRelativeResource("images/car-null.png"));
		imageForm.add(nullImg);

		Image img = new Image("car_photo", new ContextRelativeResource("images/car-null.png"));;

		if (car.getCarPhotos() != null) {
		if (car.getCarPhotos().size() != 0) {
			img = ImageResource.createImage("car_photo",car.getCarPhotos().get(0).getPhoto());
		}
		}
		imageForm.add(img);

//		CarPhotoListPanel carPhotoListPanel = new CarPhotoListPanel("carphotolist-panel", car);
//		carPhotoListPanel.setOutputMarkupId(true);
//		carPhotoListPanel.setOutputMarkupPlaceholderTag(true);
//		imageForm.add(carPhotoListPanel);

		if (car.getCarPhotos() != null) {
		if (car.getCarPhotos().size() == 0) {
			//carPhotoListPanel.setVisible(false);
			 img.setVisible(false);
		} else {
			nullImg.setVisible(false);
		}
		}
		else {
			img.setVisible(false);
		};
			

		FileUploadField photoUpload = new FileUploadField("photoUpload");
		imageForm.add(photoUpload);

		imageForm.add(new AjaxSubmitLink("load") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// TODO Auto-generated method stub
				super.onSubmit(target, form);
				FileUpload uploadedFile = photoUpload.getFileUpload();

				if (uploadedFile != null) {
					ifPhotoUpload = true;
					if (car.getCarPhotos() == null) {
						car.setCarPhotos(new ArrayList<CarPhoto>());
					}
					CarPhoto carPhoto = new CarPhoto();
					carPhoto.setCar(car);
					carPhoto.setPhoto(uploadedFile.getBytes());
					car.getCarPhotos().add(carPhoto);
					String localizedMessage = getString("user.photo.loaded");
					this.info(localizedMessage);
					target.addChildren(getPage(), FeedbackPanel.class);
				}
				;
			}
		});

		form.add(imageForm);

		FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
		feedback.setOutputMarkupId(true);

	}

}
