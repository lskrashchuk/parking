package by.lskrashchuk.training.parking.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Place extends AbstractModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Section.class, fetch = FetchType.LAZY)
	private Section section;
	
	@Column
	private Integer number;
	
	@ManyToOne(targetEntity = CarType.class, fetch = FetchType.LAZY)
	private CarType carType;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	

}
