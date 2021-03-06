package by.lskrashchuk.training.parking.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Brand extends AbstractModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private List<Model> models;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Model> getModels() {
		return models;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}
	
	

}
