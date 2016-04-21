package by.lskrashchuk.training.parking.datamodel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Model extends AbstractModel{
	
	@Column
	private String name;
	
	@ManyToOne(targetEntity = Brand.class, fetch = FetchType.LAZY)
	private Brand brand;
	
	@ManyToOne(targetEntity = CarType.class, fetch = FetchType.LAZY)
	private CarType carType;
	
	@Column
	private BigDecimal length;
	
	@Column
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
