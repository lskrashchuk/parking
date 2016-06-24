package by.lskrashchuk.training.parking.datamodel;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Section extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Blob plan;
	
	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
	private List<Place> places;
	
	@JoinTable(name = "user_type_2_section", joinColumns = { @JoinColumn (name = "section_id")}, inverseJoinColumns = { @JoinColumn (name = "user_type_id") })
	@ManyToMany (targetEntity = UserType.class, fetch = FetchType.LAZY)
	private List<UserType> userTypes;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getPlan() {
		return plan;
	}

	public void setPlan(Blob plan) {
		this.plan = plan;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<UserType> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}

	
	

}
