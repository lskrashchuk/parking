package by.lskrashchuk.training.parking.dataaccess.filters;


import by.lskrashchuk.training.parking.datamodel.Car;
import by.lskrashchuk.training.parking.datamodel.CarPhoto;

public class CarPhotoFilter extends AbstractFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Car car;
	
	private CarPhoto carPhoto;
    

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}


	public CarPhoto getCarPhoto() {
		return carPhoto;
	}

	public void setCarPhoto(CarPhoto carPhoto) {
		this.carPhoto = carPhoto;
	}
	
	
}
