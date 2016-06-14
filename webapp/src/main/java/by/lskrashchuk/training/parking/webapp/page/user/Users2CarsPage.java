package by.lskrashchuk.training.parking.webapp.page.user;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.extensions.markup.html.form.palette.theme.DefaultTheme;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.service.UserTypeService;
import by.lskrashchuk.training.parking.webapp.common.renderer.CarChoiceRenderer;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;

public class Users2CarsPage extends AbstractPage{
	
	@Inject
	private UserService userService;

	@Inject
	private CarService carService;

	private User user;

	public Users2CarsPage(PageParameters parameters) {
		super(parameters);
	}
	public Users2CarsPage(User user) {
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

        Form<User> form = new Form<User>("form", new CompoundPropertyModel<>(user));
        add(form);

        form.add(new TextField<>("firstName"));
        form.add(new TextField<>("lastName"));

        List<Car> allCars = carService.find(new CarFilter());
        final Palette<Car> palette = new Palette<Car>("cars", Model.ofList(user.getCars()), new CollectionModel<Car>(
                allCars), CarChoiceRenderer.INSTANCE, 15, false, true);
        palette.add(new DefaultTheme());
        form.add(palette);

        form.add(new SubmitLink("save") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                userService.saveOrUpdate(user);
                setResponsePage(new UsersPage());
            }
        });

}
    
    
}