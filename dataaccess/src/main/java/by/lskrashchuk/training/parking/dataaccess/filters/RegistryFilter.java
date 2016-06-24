package by.lskrashchuk.training.parking.dataaccess.filters;

public class RegistryFilter extends AbstractFilter{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String registryEventType;
    private String registryPlaceNumber;
    private String registryCarRegnumber;
    private String registryEventTime;
  
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
    
    

}
