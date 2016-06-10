package by.lskrashchuk.training.parking.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Car extends AbstractModel{
	
	@Column
	private String regNumber;
	
	@ManyToOne( targetEntity = Model.class, fetch = FetchType.EAGER)
	private Model model;

	@Column
	private Integer yearProduced;
	
	@ManyToOne( targetEntity = Color.class, fetch = FetchType.LAZY)
	private Color color;
	
	@JoinTable(name = "user_2_car", joinColumns = {@JoinColumn(name = "car_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
	@ManyToMany(targetEntity = User.class,fetch = FetchType.LAZY)
	private List<User> users;
	
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
	private List<CarPhoto> carPhotos;

	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<CarPhoto> getCarPhotos() {
		return carPhotos;
	}

	public void setCarPhotos(List<CarPhoto> carPhotos) {
		this.carPhotos = carPhotos;
	}
	
	

}
