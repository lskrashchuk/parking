package by.lskrashchuk.training.parking.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.lskrashchuk.training.parking.dataaccess.SectionDao;
import by.lskrashchuk.training.parking.datamodel.Section;

@Repository
public class SectionDaoImpl extends AbstractDaoImpl<Section, Long> implements SectionDao{

	protected SectionDaoImpl() {
		super(Section.class);
	}

}
