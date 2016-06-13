package by.lskrashchuk.training.parking.webapp.page.car.panel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;

public class CarPhotoListPanel extends Panel {


	public CarPhotoListPanel(String id, Car car) {
		super(id);
		List<Image> list = new ArrayList<Image>();
		List<CarPhoto> carPhotos = car.getCarPhotos();
		if (carPhotos.size() != 0) {
			for (CarPhoto carPhoto : carPhotos) {
				list.add(ImageResource.createImage(id, carPhoto.getPhoto()));
			}
		}

		ListView<Image> listview = new ListView<Image>("carphotolist", list) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void populateItem(final ListItem<Image> item) {
				final Image model = item.getModelObject();
				NonCachingImage i = new NonCachingImage("carphoto");
				i.setOutputMarkupId(true);
				i.setOutputMarkupPlaceholderTag(true);
//				item.add(new Image("carphoto", item.getModel()));
				item.add(i, model);
			}
			
		};
		add(listview);

	}

}
