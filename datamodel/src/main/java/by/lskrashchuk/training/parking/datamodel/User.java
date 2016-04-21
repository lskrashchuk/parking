package by.lskrashchuk.training.parking.datamodel;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User extends AbstractModel {
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private Blob photo;
	
	@Column
	private String phone;
	
	@Column (updatable = false)
	private String email;
	
	@Column
	private String password;
	
	@Column
	private Date created;
	
	@ManyToOne(targetEntity = UserType.class, fetch = FetchType.LAZY)
	private UserType userType;
	
	@Column
	@Enumerated (value = EnumType.ORDINAL)
	private UserRole userRole;
	
	@JoinTable(name = "user_2_car", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "car_id")})
	@ManyToMany(targetEntity = Car.class, fetch = FetchType.LAZY)
	private List<Car> cars;
	
	@OneToMany(mappedBy = "registry", fetch = FetchType.LAZY)
	private List<Registry> registry;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Registry> getRegistry() {
		return registry;
	}

	public void setRegistry(List<Registry> registry) {
		this.registry = registry;
	}

	

}
