package by.lskrashchuk.training.parking.webapp.page.car.panel;

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

import by.lskrashchuk.training.parking.dataaccess.filters.CarFilter;
import by.lskrashchuk.training.parking.dataaccess.filters.UserFilter;
import by.lskrashchuk.training.parking.datamodel.Brand_;
import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.Car_;
import by.lskrashchuk.training.parking.datamodel.Model_;
import by.lskrashchuk.training.parking.service.BrandService;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.service.ModelService;


public class CarListPanel extends Panel{
    @Inject
    private CarService carService;
    
    @Inject
    private BrandService brandService;
    
    @Inject
    private ModelService modelService;

    
    
	public CarListPanel(String id) {
		super(id);
        CarDataProvider carDataProvider = new CarDataProvider();
        DataView<Car> dataView = new DataView<Car>("rows", carDataProvider, 5) {
            @Override
            protected void populateItem(Item<Car> item) {
                Car car = item.getModelObject();

                item.add(new Label("id", car.getId()));
                item.add(new Label("regnumber", car.getRegNumber()));
                item.add(new Label("brand", car.getModel().getBrand().getName()));
                item.add(new Label("model", car.getModel().getName()));

                item.add(new Link<Void>("edit-link") {
                    @Override
                    public void onClick() {
   //                     setResponsePage(new CarEditPage(car));
                    }
                });

                item.add(new Link<Void>("delete-link") {
                    @Override
                    public void onClick() {
                        try {
                            carService.delete(car);
                        } catch (PersistenceException e) {
                            System.out.println("caughth persistance exception");
                        }

    //                    setResponsePage(new UsersPage());
                    }
                });

            }
        };
        add(dataView);
        add(new PagingNavigator("paging", dataView));

        add(new OrderByBorder("sort-id", Car_.id, carDataProvider));
        add(new OrderByBorder("sort-regnumber", Car_.regNumber, carDataProvider));
        add(new OrderByBorder("sort-brand", Car_.model, carDataProvider));
        add(new OrderByBorder("sort-model", Car_.model, carDataProvider));

	}


    private class CarDataProvider extends SortableDataProvider<Car, Serializable> {

        private CarFilter carFilter;

        public CarDataProvider() {
            super();
            carFilter = new CarFilter();
            setSort((Serializable) Car_.regNumber, SortOrder.ASCENDING);
        }

        @Override
        public Iterator<Car> iterator(long first, long count) {
            Serializable property = getSort().getProperty();
            SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

            carFilter.setSortProperty((SingularAttribute) property);
            carFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

            carFilter.setLimit((int) count);
            carFilter.setOffset((int) first);
            return carService.find(carFilter).iterator();
        }

        @Override
        public long size() {
            return carService.count(carFilter);
        }

        @Override
        public IModel<Car> model(Car object) {
            return new Model(object);
        }

    }



}
