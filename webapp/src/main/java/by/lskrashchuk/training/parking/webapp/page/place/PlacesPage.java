package by.lskrashchuk.training.parking.webapp.page.place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.lskrashchuk.training.parking.dataaccess.filters.PlaceFilter;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Place_;
import by.lskrashchuk.training.parking.datamodel.Section;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.service.RegistryService;
import by.lskrashchuk.training.parking.service.UserService;
import by.lskrashchuk.training.parking.webapp.page.AbstractPage;
import by.lskrashchuk.training.parking.webapp.page.user.UserEditPage;
import by.lskrashchuk.training.parking.webapp.page.user.UsersPage;

@AuthorizeInstantiation(value = { "admin" })
public class PlacesPage extends AbstractPage {

	@Inject
	private PlaceService placeService;

	@Inject
	private UserService userService;
	
	@Inject
	private RegistryService registryService;

	public PlacesPage() {
		super();
//		for (Section section : placeService.getAllSections()) {
//			add(new Label("section", section.getName()));
			PlacesDataProvider placesDataProvider = new PlacesDataProvider();
			DataView<Place> dataView = new DataView<Place>("rows", placesDataProvider) {
				@Override
				protected void populateItem(Item<Place> item) {
					Place place = item.getModelObject();

					item.add(new Label("number", place.getNumber()));
					item.add(new Label("isbusy", placeService.getIsBusy(place)));
	//				item.add(new Label("number", place.getNumber()));
					item.add(new Link<Void>("edit-link") {
						@Override
						public void onClick() {
							setResponsePage(new PlaceEditPage(place));
						}
					});

					item.add(new Link<Void>("delete-link") {
						@Override
						public void onClick() {
							try {
								placeService.delete(place.getId());
							} catch (PersistenceException e) {
								System.out.println("caughth persistance exception");
							}

							setResponsePage(new PlacesPage());
						}
					});

				}
			};
			add(dataView);
//		}
	}
		private class PlacesDataProvider extends SortableDataProvider<Place, Serializable> {

			private PlaceFilter placeFilter;

			public PlacesDataProvider() {
				super();
				placeFilter = new PlaceFilter();
				setSort((Serializable) Place_.number, SortOrder.ASCENDING);
			}

			@Override
			public Iterator<Place> iterator(long first, long count) {
				Serializable property = getSort().getProperty();
				SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

				placeFilter.setSortProperty((SingularAttribute) property);
				placeFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

				placeFilter.setLimit((int) count);
				placeFilter.setOffset((int) first);
				return placeService.find(placeFilter).iterator();
			}

			@Override
			public long size() {
				return placeService.count(placeFilter);
			}

			@Override
			public IModel<Place> model(Place object) {
				return new Model(object);
			}

		}

	

}
