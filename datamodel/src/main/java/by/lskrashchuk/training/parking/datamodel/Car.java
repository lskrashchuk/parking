package by.lskrashchuk.training.parking.datamodel;

public class Car extends AbstractModel{
	
	private String regNumber;
	
	private Model model;
	
	private CarType carType;
	
	private Integer yearProduced;
	
	private Color color;
	
	public String getRegNumber() {
		return regNumber;
	}
	
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public CarType getCarType() {
		return carType;
	}
	
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	
	public Integer getYearProduced() {
		return yearProduced;
	}
	
	public void setYearProduced(Integer yearProduced) {
		this.yearProduced = yearProduced;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	

}
