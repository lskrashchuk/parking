package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.PlaceFilter;
import by.lskrashchuk.training.parking.datamodel.Place;

public interface PlaceDao extends AbstractDao<Place, Long>{

	Long count(PlaceFilter filter);

	List<Place> find(PlaceFilter filter);
}
