package by.lskrashchuk.training.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import by.lskrashchuk.training.parking.dataaccess.filters.ModelFilter;
import by.lskrashchuk.training.parking.datamodel.Model;

public interface ModelService {
	
	@Transactional
	void register(Model model);
	
	Model getModel(Long id);
	
	@Transactional
	void update(Model model);
	
	@Transactional
	void delete(Long id);
	
    List<Model> find(ModelFilter filter);

    List<Model> getAll();
    
    Model getByName(String modelName);


}
