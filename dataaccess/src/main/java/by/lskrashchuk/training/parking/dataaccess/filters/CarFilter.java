package by.lskrashchuk.training.parking.dataaccess.filters;

import javax.persistence.metamodel.SingularAttribute;

public class CarFilter {
    
	private String carRegNumber;
    
	private SingularAttribute sortProperty;
    
	private boolean sortOrder;
    
	private Integer offset;
    
	private Integer limit;
	
    public String getCarGegNumber() {
		return carRegNumber;
	}
	public void setCarRegNumber(String carRegNumber) {
		this.carRegNumber = carRegNumber;
	}
	public SingularAttribute getSortProperty() {
		return sortProperty;
	}
	public void setSortProperty(SingularAttribute sortProperty) {
		this.sortProperty = sortProperty;
	}
	public boolean isSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(boolean sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}



}