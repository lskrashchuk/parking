package by.lskrashchuk.training.parking.webapp.page.user;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;




import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;


import by.lskrashchuk.training.parking.dataaccess.filters.UserTypeFilter;
import by.lskrashchuk.training.parking.datamodel.Role;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.UserType;
import by.lskrashchuk.training.parking.datamodel.UserType_;
import by.lskrashchuk.training.parking.datamodel.User_;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.service.UserTypeService;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserRoleChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.common.renderer.UserTypeChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.component.login.LoginPanel;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.panel.CarListPanel;

public class UserEditPage extends AbstractPage{
	
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

/*        TextField<Double> basePriceField = new TextField<>("basePrice");
        basePriceField.add(RangeValidator.<Double> range(0d, 1_000_000d));
        basePriceField.setRequired(true); */

 
          
        DateTextField createdField = new DateTextField("created", "dd-MM-yyyy");
        createdField.add(new DatePicker());
        createdField.setRequired(true);
        form.add(createdField); 

  /*      form.add(basePriceField);

        CheckBox activeField = new CheckBox("active");
        form.add(activeField); */

        TextField<String> phoneField = new TextField<>("phone");
        phoneField.setRequired(true);
        form.add(phoneField);

        TextField<String> emailField = new TextField<>("email");
        emailField.setRequired(true);
        form.add(emailField);
       
        TextField<String> passwordField = new TextField<>("password");
        passwordField.setRequired(true);
        form.add(passwordField);
      

  //      List<UserType> allUserTypes = userTypeService.find(new UserTypeFilter());
  //      DropDownChoice<UserType> typeField = new DropDownChoice<>("userType", allUserTypes, UserTypeChoiceRenderer.INSTANCE);
 //       DropDownChoice<UserType> typeField = new DropDownChoice<>("userType", userTypeService.getAll(), UserTypeChoiceRenderer.INSTANCE);;
        DropDownChoice<UserType> typeField = new DropDownChoice<>("userType", userService.getAllUserTypes(), UserTypeChoiceRenderer.INSTANCE);;
        typeField.setRequired(true);
        form.add(typeField);
        
        DropDownChoice<Role> roleField = new DropDownChoice<>("role", Arrays.asList(Role.values()), UserRoleChoiceRenderer.INSTANCE);
        roleField.setRequired(true);
        form.add(roleField);
        
        form.add(new SubmitLink("save") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                userService.saveOrUpdate(user);
                setResponsePage(new UsersPage());
            }
        });
        
   /*     Image photoField = new Image("photo", "image");
        form.add(photoField);*/
        form.add(new SubmitLink("load") {
            @Override
            public void onSubmit() {
                super.onSubmit();
 //               userService.saveOrUpdate(user);
 //               setResponsePage(new UsersPage());
            }
        });
       
        
        form.add(new CarListPanel("carlist-panel"));
        
        form.add(new SubmitLink("editlist") {
            @Override
            public void onSubmit() {
                super.onSubmit();
 //               userService.saveOrUpdate(user);
 //               setResponsePage(new UsersPage());
            }
        });
  
        add(new FeedbackPanel("feedback"));

	}

}
