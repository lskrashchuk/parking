package by.lskrashchuk.training.parking.dataaccess.filters;

public class CarFilter extends AbstractFilter{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String carRegNumber;
  
	
    public String getCarRegNumber() {
		return carRegNumber;
	}
	public void setCarRegNumber(String carRegNumber) {
		this.carRegNumber = carRegNumber;
	}


}
