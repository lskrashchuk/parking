package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.ColorDao;
import by.lskrashchuk.training.parking.datamodel.Color;

@Repository
public class ColorDaoImpl extends AbstractDaoImpl<Color, Long> implements ColorDao{
	protected ColorDaoImpl() {
		super(Color.class);
		
	}

}
