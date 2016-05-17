package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.PlaceDao;
import by.lskrashchuk.training.parking.datamodel.Place;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Place, Long> implements PlaceDao{

	protected PlaceDaoImpl() {
		super(Place.class);
	}

}
