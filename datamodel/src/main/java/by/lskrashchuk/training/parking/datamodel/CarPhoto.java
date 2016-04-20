package by.lskrashchuk.training.parking.datamodel;

import java.sql.Blob;

public class CarPhoto extends AbstractModel{

	private Car car;
	
	private Blob photo;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	
	
	
}
