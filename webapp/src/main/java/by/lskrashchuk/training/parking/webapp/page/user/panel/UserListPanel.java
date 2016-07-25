package by.lskrashchuk.training.parking.webapp.page.user.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.webapp.page.user.UserEditPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;

public class UserListPanel extends Panel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private UserService userService;
	
	private UserFilter userFilter;
    
	public UserListPanel(String id, UserFilter userFilter) {
		super(id);
		
		this.userFilter = userFilter;
        UsersDataProvider usersDataProvider = new UsersDataProvider();
        DataView<User> dataView = new DataView<User>("rows", usersDataProvider, 5) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void populateItem(Item<User> item) {
                User user = item.getModelObject();

                item.add(new Label("id", user.getId()));
                item.add(new Label("fname", user.getFirstName()));
                item.add(new Label("lname", user.getLastName()));
                item.add(DateLabel.forDatePattern("created", Model.of(user.getCreated()), "dd-MM-yyyy"));

           //     CheckBox checkbox = new CheckBox("active", Model.of(product.getActive()));
           //     checkbox.setEnabled(false);
           //     item.add(checkbox);
                item.add(new Link<Void>("edit-link") {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public void onClick() {
                        setResponsePage(new UserEditPage(user));
                    }
                });

                item.add(new Link<Void>("delete-link") {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public void onClick() {
                        try {
                            userService.delete(user);
                        } catch (PersistenceException e) {
                            System.out.println("caughth persistance exception");
                        }

                        setResponsePage(new UsersPage());
                    }
                });

            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", User_.id, usersDataProvider));
        add(new OrderByBorder("sort-fname", User_.firstName, usersDataProvider));
        add(new OrderByBorder("sort-lname", User_.lastName, usersDataProvider));

	}


    private class UsersDataProvider extends SortableDataProvider<User, Serializable> {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
//		private UserFilter userFilter;

        public UsersDataProvider() {
            super();
//            userFilter = new UserFilter();
            setSort((Serializable) User_.lastName, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<User> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            userFilter.setSortProperty((SingularAttribute) property);
            userFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            userFilter.setLimit((int) count);
            userFilter.setOffset((int) first);
            return userService.find(userFilter).iterator();
        }

        @Override
        public long size() {
            return userService.count(userFilter);
        }

        @Override
        public IModel<User> model(User object) {
            return new Model(object);
        }

    }


	public UserFilter getUserFilter() {
		return userFilter;
	}


	public void setUserFilter(UserFilter userFilter) {
		this.userFilter = userFilter;
	}


}
