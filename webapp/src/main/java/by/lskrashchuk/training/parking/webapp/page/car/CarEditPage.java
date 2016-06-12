package by.lskrashchuk.training.parking.webapp.page.car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import org.apache.wicket.validation.validator.RangeValidator;

import by.lskrashchuk.training.parking.datamodel.Brand;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.datamodel.Role;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.service.BrandService;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.ModelService;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.service.UserTypeService;
import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarBrandChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarModelChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserRoleChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.car.panel.CarPhotoListPanel;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;
import by.lskrashchuk.training.parking.webapp.page.user.panel.CarListPanel;

public class CarEditPage extends AbstractPage{

	@Inject
	private CarService carService;

	@Inject
	private BrandService brandService;

	@Inject
	private ModelService modelService;

	private Car car;

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
		
		Form form = new Form("form");
		add(form);

		Form<Car> textForm = new Form<Car>("textForm", new CompoundPropertyModel<Car>(car));

		form.add(textForm);

		TextField<String> regnumberField = new TextField<>("regNumber");
        regnumberField.setLabel(new ResourceModel("car.regnumber"));
		regnumberField.setRequired(true);
		textForm.add(regnumberField);

		DropDownChoice<Brand> brandField = new DropDownChoice<>("brand", carService.getAllBrands(),
				CarBrandChoiceRenderer.INSTANCE);
        brandField.setLabel(new ResourceModel("car.brand"));
		brandField.setRequired(true);
		textForm.add(brandField);

		DropDownChoice<Model> modelField = new DropDownChoice<>("model", carService.getAllModels(brandService.getBrand(car.getBrand().getId())),
				CarModelChoiceRenderer.INSTANCE);
        modelField.setLabel(new ResourceModel("car.model"));
		modelField.setRequired(true);
		textForm.add(modelField); 

		TextField yearproducedField = new TextField("yearProduced");
		Calendar calendar = Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
		calendar.setTime(new Date());
		int currentYear = calendar.get(java.util.Calendar.YEAR); 
		yearproducedField.add(RangeValidator.<Integer> range(1900, currentYear));
        yearproducedField.setLabel(new ResourceModel("car.yearproduced"));
		yearproducedField.setRequired(true);
		textForm.add(yearproducedField);


		textForm.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();

				carService.saveOrUpdate(car);
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

		CarPhotoListPanel carPhotoListPanel = new CarPhotoListPanel("carlist-panel", car);
		imageForm.add(carPhotoListPanel);

		
		List<CarPhoto> carPhotos = car.getCarPhotos();
		if (car.getCarPhotos().size()==0) {
			carPhotoListPanel.setVisible(false);
		}
		else {
			nullImg.setVisible(false);
		} 

		FileUploadField photoUpload = new FileUploadField("photoUpload");
		imageForm.add(photoUpload);


      
        imageForm.add(new AjaxSubmitLink("load") {
        	
        	@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// TODO Auto-generated method stub
				super.onSubmit(target, form);
				FileUpload uploadedFile = photoUpload.getFileUpload();
		
			
				if (uploadedFile != null) {
					if (car.getCarPhotos()== null) {
						car.setCarPhotos(new ArrayList<CarPhoto>());				
					}
					CarPhoto carPhoto = new CarPhoto();
					carPhoto.setCar(car);
					carPhoto.setPhoto(uploadedFile.getBytes());
					car.getCarPhotos().add(carPhoto);
					String localizedMessage = getString("user.photo.loaded");
	                this.info(localizedMessage);
	                target.addChildren(getPage(), FeedbackPanel.class);
				};
			}
       });



        form.add(imageForm);

		FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
		feedback.setOutputMarkupId(true);
		

	}
	



}