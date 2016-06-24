package by.lskrashchuk.training.parking.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class CarPhoto extends AbstractModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	private Car car;
	
	@Column
	private byte[] photo;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	
	
}
