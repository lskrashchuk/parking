package by.lskrashchuk.training.parking.webapp.page.home;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.webapp.app.AuthorizedSession;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;


@AuthorizeInstantiation(value = { "admin", "manager" })
public class HomePage extends AbstractPage{
    public HomePage() {
        super();
        
        User loggedUser = AuthorizedSession.get().getLoggedUser();
        Roles roles = AuthorizedSession.get().getRoles();

    }

}
