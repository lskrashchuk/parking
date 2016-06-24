package by.lskrashchuk.training.parking.dataaccess.filters;


public class BrandFilter extends AbstractFilter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String brandName;
    

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


}
