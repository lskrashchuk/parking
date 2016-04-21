package by.lskrashchuk.training.parking.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class UserType extends AbstractModel{
	
	@Column
	private String name;

	@JoinTable(name = "user_type_2_section", joinColumns = {@JoinColumn (name = "user_type_id")}, inverseJoinColumns = {@JoinColumn (name = "section_id")})
	@ManyToMany(targetEntity = Section.class, fetch = FetchType.LAZY)
	private List<Section> sections;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<User> users;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
