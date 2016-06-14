package by.lskrashchuk.training.parking.webapp.page.user;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.panel.UserListPanel;

@AuthorizeInstantiation(value = { "admin", "manager" })
public class UsersPage extends AbstractPage{

	@Inject
	private UserService userService;
	
	public UsersPage() {
		super();
        add(new UserListPanel("list-panel"));
 
        add(new Link("create") {
            @Override
            public void onClick() {
                setResponsePage(new UserEditPage(new User()));
            }
        });
        
        add(new FeedbackPanel("feedback"));
 
	}

}
