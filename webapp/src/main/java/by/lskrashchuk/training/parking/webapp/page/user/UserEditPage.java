package by.lskrashchuk.training.parking.webapp.page.user;


import java.util.Arrays;

import javax.inject.Inject;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.RangeValidator;

import by.lskrashchuk.training.parking.dataaccess.filters.UserTypeFilter;
import by.lskrashchuk.training.parking.datamodel.Role;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.datamodel.UserType_;
import by.lskrashchuk.training.parking.datamodel.User_;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.service.UserTypeService;

import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserRoleChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserTypeChoiceRenderer;

import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

import by.lskrashchuk.training.parking.webapp.page.user.panel.CarListPanel;

public class UserEditPage extends AbstractPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	@Inject
	private UserTypeService userTypeService;

	private User user;

	public UserEditPage(PageParameters parameters) {
		super(parameters);
	}

	public UserEditPage(User user) {
		super();
		if (user.getId() == null) {
			this.user = user;
		} else {
			this.user = userService.getUser(user.getId());
		}

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<User> form = new Form<User>("form", new CompoundPropertyModel<User>(user));

		add(form);

		TextField<String> fnameField = new TextField<>("firstName");
		fnameField.setRequired(true);
		form.add(fnameField);

		TextField<String> lnameField = new TextField<>("lastName");
		lnameField.setRequired(true);
		form.add(lnameField);

		/*
		 * TextField<Double> basePriceField = new TextField<>("basePrice");
		 * basePriceField.add(RangeValidator.<Double> range(0d, 1_000_000d));
		 * basePriceField.setRequired(true);
		 */

		DateTextField createdField = new DateTextField("created", "dd-MM-yyyy");
		createdField.add(new DatePicker());
		createdField.setRequired(true);
		form.add(createdField);

		/*
		 * form.add(basePriceField);
		 * 
		 * CheckBox activeField = new CheckBox("active"); form.add(activeField);
		 */

		TextField<String> phoneField = new TextField<>("phone");
		phoneField.setRequired(true);
		form.add(phoneField);

		TextField<String> emailField = new TextField<>("email");
		emailField.setRequired(true);
		form.add(emailField);

		TextField<String> passwordField = new TextField<>("password");
		passwordField.setRequired(true);
		form.add(passwordField);

		// List<UserType> allUserTypes = userTypeService.find(new
		// UserTypeFilter());
		// DropDownChoice<UserType> typeField = new DropDownChoice<>("userType",
		// allUserTypes, UserTypeChoiceRenderer.INSTANCE);
		// DropDownChoice<UserType> typeField = new DropDownChoice<>("userType",
		// userTypeService.getAll(), UserTypeChoiceRenderer.INSTANCE);;
		DropDownChoice<UserType> typeField = new DropDownChoice<>("userType", userService.getAllUserTypes(),
				UserTypeChoiceRenderer.INSTANCE);
		;
		typeField.setRequired(true);
		form.add(typeField);

		DropDownChoice<Role> roleField = new DropDownChoice<>("role", Arrays.asList(Role.values()),
				UserRoleChoiceRenderer.INSTANCE);
		roleField.setRequired(true);
		form.add(roleField);

		/*
		 * form.add(new SubmitLink("load") {
		 * 
		 * @Override public void onSubmit() { super.onSubmit(); //
		 * userService.saveOrUpdate(user); setResponsePage(new
		 * FileUploadPage()); // setResponsePage(new UsersPage()); } });
		 * 
		 */
		// FileUploadField photoUpload = new FileUploadField("photo");
		// form.add(photoUpload);

		form.add(new CarListPanel("carlist-panel"));

		form.add(new SubmitLink("editlist") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				// userService.saveOrUpdate(user);
				// setResponsePage(new UsersPage());
			}
		});

		form.add(new SubmitLink("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();

				userService.saveOrUpdate(user);
				setResponsePage(new UsersPage());
			}
		});

		Form<Void> imageForm = new Form<Void>("imageForm");
		imageForm.setOutputMarkupId(true);
		// Enable multipart mode (need for uploads file)
		imageForm.setMultiPart(true);

		// max upload size, 100k
		imageForm.setMaxSize(Bytes.kilobytes(100));

		/*
		 * File file = new File("d:\\photo3f.jpg"); try {
		 * user.setPhoto(ImageResource.getBytesFromFile(file)); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		byte[] data = user.getPhoto();
		Image img = ImageResource.createImage("orig_photo", data);
		img.setOutputMarkupId(true);
		img.setOutputMarkupPlaceholderTag(true);

		imageForm.addOrReplace(img);

		FileUploadField photoUpload = new FileUploadField("photo");
		imageForm.add(photoUpload);


		Model<Image> imageModel = new Model<Image>();
        imageModel.setObject(img);
        
        imageForm.add(new AjaxLink<Void>("load") {
			private static final long serialVersionUID = 1L;

				@Override
	            public void onClick(AjaxRequestTarget target) {
	                loadAndUpdate(imageModel, img, photoUpload, target);
	            }
	        });


	       

		/*
		 * photoUpload.add(new AjaxEventBehavior("onchange") {
		 * 
		 * @Override protected void onEvent(AjaxRequestTarget target) { byte[] b
		 * = photoUpload.getFileUpload().getBytes(); Image img1 =
		 * ImageResource.createImage("orig_photo",
		 * photoUpload.getFileUpload().getBytes());
		 * 
		 * imageForm.addOrReplace(img1);
		 * img.add(AttributeModifier.replace("src", "d://photo3f.jpg"));
		 * imageForm.addOrReplace(img);
		 * 
		 * target.add(img);
		 * 
		 * 
		 * } });
		 */

		/*
		 * Model<Integer> counterModel = new Model<Integer>();
		 * counterModel.setObject(0); Label label = new Label("counter",
		 * counterModel) {
		 * 
		 * @Override public void onEvent(IEvent<?> event) { if
		 * (event.getPayload() instanceof ProductChangeEvent) {
		 * counterModel.setObject(0); } } }; label.setOutputMarkupId(true);
		 * label.setOutputMarkupPlaceholderTag(true); add(label);
		 * 
		 * add(new AjaxLink<Void>("btn-increment") {
		 * 
		 * @Override public void onClick(AjaxRequestTarget target) {
		 * incrementAndUpdate(counterModel, label, target); } });
		 */

		form.add(imageForm);

		add(new FeedbackPanel("feedback"));

	}
	
    private void loadAndUpdate(Model<Image> imageModel, Image img, FileUploadField photoUpload, AjaxRequestTarget target) {
		if (photoUpload != null) {
			user.setPhoto(photoUpload.getFileUpload().getBytes());
		};

        target.add(img);
		setResponsePage(new UsersPage());

    }


}
