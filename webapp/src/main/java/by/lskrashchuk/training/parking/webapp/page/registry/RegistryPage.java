package by.lskrashchuk.training.parking.webapp.page.registry;

import java.io.Serializable;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.lskrashchuk.training.parking.dataaccess.filters.RegistryFilter;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.datamodel.Registry_;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.RegistryService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.UserEditPage;

@AuthorizeInstantiation(value = { "admin", "guard" })

public class RegistryPage extends AbstractPage {
	@Inject
	private RegistryService registryService;

	public RegistryPage() {

		RegistryDataProvider registryDataProvider = new RegistryDataProvider();
		DataView<Registry> dataView = new DataView<Registry>("rows", registryDataProvider, 5) {
			@Override
			protected void populateItem(Item<Registry> item) {
				Registry registry = item.getModelObject();

				item.add(new Label("id", registry.getId()));
				item.add(new Label("place", registry.getPlace().getNumber()));
				item.add(new Label("car", registry.getCar().getRegNumber()));
				item.add(new Label("event", registry.getEventType()));
				item.add(DateLabel.forDatePattern("time", Model.of(registry.getEventTime()), "dd-MM-yyyy hh:mm"));

				// CheckBox checkbox = new CheckBox("active",
				// Model.of(product.getActive()));
				// checkbox.setEnabled(false);
				// item.add(checkbox);
				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new RegistryEditPage(registry));
					}
				});

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							registryService.delete(registry.getId());
						} catch (PersistenceException e) {
							System.out.println("caughth persistance exception");
						}

						setResponsePage(new RegistryPage());
					}
				});

			}
		};
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id", Registry_.id, registryDataProvider));
		add(new OrderByBorder("sort-place", Registry_.place, registryDataProvider));
		add(new OrderByBorder("sort-car", Registry_.car, registryDataProvider));
		add(new OrderByBorder("sort-event", Registry_.eventType, registryDataProvider));
		add(new OrderByBorder("sort-time", Registry_.eventTime, registryDataProvider));
 
		add(new Link("create") {
            @Override
            public void onClick() {
                setResponsePage(new RegistryEditPage(new Registry()));
            }
        });
        
        add(new FeedbackPanel("feedback"));

	}

	private class RegistryDataProvider extends SortableDataProvider<Registry, Serializable> {

		private RegistryFilter registryFilter;

		public RegistryDataProvider() {
			super();
			registryFilter = new RegistryFilter();
			setSort((Serializable) Registry_.eventTime, SortOrder.DESCENDING);
		}

		@Override
		public Iterator<Registry> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			registryFilter.setSortProperty((SingularAttribute) property);
			registryFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			registryFilter.setLimit((int) count);
			registryFilter.setOffset((int) first);
			return registryService.find(registryFilter).iterator();
		}

		@Override
		public long size() {
			return registryService.count(registryFilter);
		}

		@Override
		public IModel<Registry> model(Registry object) {
			return new Model(object);
		}

	}

}
