package by.lskrashchuk.training.parking.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.lskrashchuk.training.parking.dataaccess.ColorDao;
import by.lskrashchuk.training.parking.datamodel.Color;
import by.lskrashchuk.training.parking.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{

	@Inject
	private ColorDao colorDao;
	
	@Override
	public void register(Color color) {
		colorDao.insert(color);
	}

	@Override
	public void update(Color color) {
		colorDao.update(color);
	}

	@Override
	public void delete(Long id) {
		colorDao.delete(id);
	}

	@Override
	public Color getColor(Long id) {
		return colorDao.get(id);
		
	}

}

