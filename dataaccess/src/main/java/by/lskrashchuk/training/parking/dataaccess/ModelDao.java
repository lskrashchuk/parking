package by.lskrashchuk.training.parking.dataaccess;

import by.lskrashchuk.training.parking.datamodel.Model;

public interface ModelDao {
	
	Model get(Long id);
	
	Model save();

}
