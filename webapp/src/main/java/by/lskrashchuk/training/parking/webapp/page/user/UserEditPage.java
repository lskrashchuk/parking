package by.lskrashchuk.training.parking.webapp.page.user;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;

import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class UserEditPage extends AbstractPage{
	
	@Inject
	private UserService userService;
	
	private User user;

	public UserEditPage(PageParameters parameters) {
		super(parameters);
	}

	public UserEditPage(User user) {
		super();
		this.user = user;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
        Form form = new Form("form", new CompoundPropertyModel<User>(user));
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

        DateTextField createdField = new DateTextField("created");
        createdField.add(new DatePicker());
        createdField.setRequired(true);
        form.add(createdField);

  /*      form.add(basePriceField);

        CheckBox activeField = new CheckBox("active");
        form.add(activeField); */

        form.add(new SubmitLink("save") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                userService.saveOrUpdate(user);
                setResponsePage(new UsersPage());
            }
        });

        add(new FeedbackPanel("feedback"));

	}

}
