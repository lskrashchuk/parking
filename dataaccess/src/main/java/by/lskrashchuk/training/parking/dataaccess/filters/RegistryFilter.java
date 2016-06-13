package by.lskrashchuk.training.parking.dataaccess.filters;

import javax.persistence.metamodel.SingularAttribute;

public class RegistryFilter {

    private String registryEventType;
    private String registryPlaceNumber;
    private String registryCarRegnumber;
    private String registryEventTime;
  
    private SingularAttribute sortProperty;
    private boolean sortOrder;
    private Integer offset;
    private Integer limit;
	public String getRegistryEventType() {
		return registryEventType;
	}
	public void setRegistryEventType(String registryEventType) {
		this.registryEventType = registryEventType;
	}
	public String getRegistryPlaceNumber() {
		return registryPlaceNumber;
	}
	public void setRegistryPlaceNumber(String registryPlaceNumber) {
		this.registryPlaceNumber = registryPlaceNumber;
	}
	public String getRegistryCarRegnumber() {
		return registryCarRegnumber;
	}
	public void setRegistryCarRegnumber(String registryCarRegnumber) {
		this.registryCarRegnumber = registryCarRegnumber;
	}
	public String getRegistryEventTime() {
		return registryEventTime;
	}
	public void setRegistryEventTime(String registryEventTime) {
		this.registryEventTime = registryEventTime;
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
