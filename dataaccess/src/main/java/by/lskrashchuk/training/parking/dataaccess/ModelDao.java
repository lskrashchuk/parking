package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.ModelFilter;
import by.lskrashchuk.training.parking.datamodel.Model;

public interface ModelDao extends AbstractDao<Model, Long>{
	
	Model get(Long id);
	
	List<Model> find(ModelFilter filter);

}
