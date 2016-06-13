package by.lskrashchuk.training.parking.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.CarDao;
import by.lskrashchuk.training.parking.dataaccess.ModelDao;
import by.lskrashchuk.training.parking.dataaccess.filters.ModelFilter;
import by.lskrashchuk.training.parking.datamodel.Model;
import by.lskrashchuk.training.parking.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService{

	private static Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

	@Inject
	private ModelDao modelDao;

	@Override
	public void register(Model model) {
		modelDao.insert(model);
        LOGGER.info("Model regirstred: {}", model);
		
	}

	@Override
	public Model getModel(Long id) {
		return modelDao.get(id);
	}

	@Override
	public void update(Model model) {
		modelDao.update(model);
		LOGGER.info("Model updated: {}", model);
	}

	@Override
	public void delete(Long id) {
		modelDao.delete(id);
        LOGGER.info("Model deleted, id: {}", id);
		
	}

	@Override
	public List<Model> find(ModelFilter filter) {
		return modelDao.find(filter);
	}

	@Override
	public List<Model> getAll() {
		return modelDao.getAll();
	}

	@Override
	public Model getByName(String modelName) {
		return modelDao.find(modelName);
	}

}
