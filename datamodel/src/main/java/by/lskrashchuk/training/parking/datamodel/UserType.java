package by.lskrashchuk.training.parking.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserType extends AbstractModel{
	
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
