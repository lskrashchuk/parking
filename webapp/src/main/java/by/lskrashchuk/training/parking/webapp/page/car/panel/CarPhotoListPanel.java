package by.lskrashchuk.training.parking.webapp.page.car.panel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.CarPhotoService;
import by.lskrashchuk.training.parking.service.CarService;
import by.lskrashchuk.training.parking.webapp.common.image.ImageResource;

public class CarPhotoListPanel extends Panel{
	
		
		@Inject
		private CarPhotoService carPhotoService;

		public CarPhotoListPanel(String id, Car car) {
			super(id);
			List<Image> list = new ArrayList<Image>();
			for(CarPhoto carPhoto : car.getCarPhotos()){
				list.add(ImageResource.createImage(id, carPhoto.getPhoto()));
			}

			ListView listview = new ListView("carphotolist", list) {
			    protected void populateItem(ListItem item) {
			        item.add(new Image("carphoto", item.getModel()));
			    }
			};
			add(listview);
		}

	


}
