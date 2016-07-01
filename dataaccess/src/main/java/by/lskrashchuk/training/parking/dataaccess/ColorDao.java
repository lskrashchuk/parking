package by.lskrashchuk.training.parking.dataaccess;

import java.util.List;

import by.lskrashchuk.training.parking.dataaccess.filters.ColorFilter;
import by.lskrashchuk.training.parking.datamodel.Color;

public interface ColorDao extends AbstractDao<Color, Long>{
	
	Color get(Long id);
	
	List<Color> find(ColorFilter filter);

	Color find(String colorName);

}
