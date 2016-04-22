package by.lskrashchuk.training.parking.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class CarType extends AbstractModel {

	@Column
	private String name;
	
	@Column
	private Integer rank;

	@OneToMany(mappedBy = "carType", fetch = FetchType.LAZY)
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	

}
