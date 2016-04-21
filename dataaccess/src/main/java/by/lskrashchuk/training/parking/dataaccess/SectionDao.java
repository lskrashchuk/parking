package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Section;

public interface SectionDao {
	
	Section get(Long id);
	
	Section save();

}
