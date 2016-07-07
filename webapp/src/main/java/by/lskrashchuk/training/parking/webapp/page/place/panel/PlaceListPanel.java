package by.lskrashchuk.training.parking.webapp.page.place.panel;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;

import by.lskrashchuk.training.parking.dataaccess.filters.PlaceFilter;
import by.lskrashchuk.training.parking.datamodel.EventType;
import by.lskrashchuk.training.parking.datamodel.Place;
import by.lskrashchuk.training.parking.datamodel.Place_;
import by.lskrashchuk.training.parking.datamodel.Registry;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.PlaceService;
import by.lskrashchuk.training.parking.webapp.page.car.CarEditPage;
import by.lskrashchuk.training.parking.webapp.page.place.PlaceEditPage;
import by.lskrashchuk.training.parking.webapp.page.place.PlacesPage;
import by.lskrashchuk.training.parking.webapp.page.registry.RegistryEditPage;

public class PlaceListPanel extends Panel{
	@Inject
	private PlaceService placeService;

	@Inject
	private CarService carService;

	public PlaceListPanel(String id) {
		super(id);
//		for (Section section : placeService.getAllSections()) {
//			add(new Label("section", section.getName()));
			PlacesDataProvider placesDataProvider = new PlacesDataProvider();
			DataView<Place> dataView = new DataView<Place>("rows", placesDataProvider) {
				@Override
				protected void populateItem(Item<Place> item) {
					Place place = item.getModelObject();

					item.add(new Label("number", place.getNumber()));

					Link<Void> freeLink = new Link<Void>("free-link"){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							Registry registry = new Registry();
							registry.setEventTime(new Date());
							registry.setEventType(EventType.arrived);
							registry.setPlace(place);
							setResponsePage(new RegistryEditPage(registry));
						}
					};	
					item.add(freeLink);

					Image freeImg = new Image("free_place", new ContextRelativeResource("images/parking.png"));
					freeLink.add(freeImg);
					if (placeService.getIsBusy(place)) {
						freeLink.setVisible(false);
						freeImg.setVisible(false);
					}

					String reg = "";
					if (placeService.getIsBusy(place)) {
						reg = carService.getCar(placeService.getCarId(place)).getRegNumber();
					};
					Label buzyCar = new Label("regnumber",reg);	

					Link<Void> buzyCarLink = new Link<Void>("regnumber-link"){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							setResponsePage(new CarEditPage(carService.getCar(placeService.getCarId(place))));
						}
					};	
					item.add(buzyCarLink);
					buzyCarLink.add(buzyCar);

					Link<Void> departCarLink = new Link<Void>("depart-link"){

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							Registry registry = new Registry();
							registry.setEventTime(new Date());
							registry.setEventType(EventType.departed);
							registry.setPlace(place);
							registry.setCar(carService.getCar(placeService.getCarId(place)));
							setResponsePage(new RegistryEditPage(registry));
						}
					};	
					item.add(departCarLink);
					if (!placeService.getIsBusy(place)) {
						departCarLink.setVisible(false);
					}

					

					item.add(new Link<Void>("edit-link") {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void onClick() {
							setResponsePage(new PlaceEditPage(place));
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
