package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import by.lskrashchuk.training.parking.dataaccess.filters.ModelFilter;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.datamodel.User_;

public interface ModelDao extends AbstractDao<Model, Long>{
	
	Model get(Long id);
	
	List<Model> find(ModelFilter filter);
	
	Model find(String modelName);


}
