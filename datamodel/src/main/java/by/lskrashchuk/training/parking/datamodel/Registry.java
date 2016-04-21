package by.lskrashchuk.training.parking.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Registry extends AbstractModel{
	
	@ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	private Car car;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User user;
	
	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private EventType eventType;
	
	@Column
	private Date eventTime;
	
	@ManyToOne(targetEntity = Place.class, fetch = FetchType.LAZY)
	private Place place;
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}

	public Place getPlace() {
		return place;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getEventTime() {
		return eventTime;
	}
	
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	

}
