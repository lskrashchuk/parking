package by.lskrashchuk.training.parking.datamodel;

import java.math.BigDecimal;

public class Model extends AbstractModel{
	
	private String name;
	
	private Brand brand;
	
	private CarType carType;
	
	private BigDecimal length;
	
	private BigDecimal width;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	
	
	

}
