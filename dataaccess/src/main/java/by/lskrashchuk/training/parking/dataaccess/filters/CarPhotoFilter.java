package by.lskrashchuk.training.parking.dataaccess.filters;

import javax.persistence.metamodel.SingularAttribute;

import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;

public class CarPhotoFilter {

	private Car car;
	
	private CarPhoto carPhoto;
    
	private Integer offset;
    
	private Integer limit;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public CarPhoto getCarPhoto() {
		return carPhoto;
	}

	public void setCarPhoto(CarPhoto carPhoto) {
		this.carPhoto = carPhoto;
	}
	
	
}
