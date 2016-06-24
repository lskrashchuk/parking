package by.lskrashchuk.training.parking.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Color extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@OneToMany(mappedBy = "color", fetch = FetchType.LAZY)
	private List<Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	

}
