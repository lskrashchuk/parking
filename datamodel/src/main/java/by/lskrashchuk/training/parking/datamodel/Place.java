package by.lskrashchuk.training.parking.datamodel;

public class Place extends AbstractModel{
	
	private Section section;
	
	private Integer number;
	
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
