package by.lskrashchuk.training.parking.datamodel;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CarPhoto extends AbstractModel{

	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	private Car car;
	
	@Column
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
