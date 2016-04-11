package by.lskrashchuk.training.parking.datamodel;

public class Place extends AbstractModel{
	
	private Section section;
	
	private int number;
	
	private PlaceSize placeSize;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public PlaceSize getPlaceSize() {
		return placeSize;
	}

	public void setPlaceSize(PlaceSize placeSize) {
		this.placeSize = placeSize;
	}
	
	
	

}
