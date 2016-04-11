package by.lskrashchuk.training.parking.datamodel;

import java.math.BigDecimal;

public class PlaceSize extends AbstractModel{
	
	private String name;
	
	private BigDecimal length;
	
	private BigDecimal width;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	
	
	

}
