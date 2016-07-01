package by.lskrashchuk.training.parking.service.impl;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.ColorDao;
import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.datamodel.User;
import by.lskrashchuk.training.parking.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{
    private static Logger LOGGER = LoggerFactory.getLogger(ColorServiceImpl.class);

	@Inject
	private ColorDao colorDao;
	
	@Override
	public void register(Color color) {
		colorDao.insert(color);
        LOGGER.info("Color regirstred: {}", color);
	}

	@Override
	public void update(Color color) {
		colorDao.update(color);
        LOGGER.info("Color updated: {}", color);
	}

	@Override
	public void delete(Long id) {
		colorDao.delete(id);
        LOGGER.info("Color deleted, id: {}", id);
	}

	@Override
	public Color getColor(Long id) {
		return colorDao.get(id);
		
	}

	@Override
	public void saveOrUpdate(Color color) {
		if (color.getId() == null) {
			colorDao.insert(color);
			LOGGER.info("Color inserted: {}", color);
		} else {
			colorDao.update(color);
			LOGGER.info("Color updated: {}", color);
		}
	}

	@Override
	public Color getByName(String colorName) {
		return colorDao.find(colorName);
	}

}

