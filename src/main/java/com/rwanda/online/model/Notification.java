package com.rwanda.online.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notifications")

public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notification_id")
	private Long id;
	
	@Column(name = "notificationowner", nullable = false, length = 20)
	private Long notificationowner;
	@Column(name = "message", nullable = false, length = 20)
	private String message;
	@Column(name = "isRead")
	private boolean isRead;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tripId")
	private Trip trip;

	public Notification() {
		super();
	}

	public Notification(Long notificationowner, String message, boolean isRead, Trip trip) {
		super();
		this.notificationowner = notificationowner;
		this.message = message;
		this.isRead = isRead;
		this.trip = trip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNotificationowner() {
		return notificationowner;
	}

	public void setNotificationowner(Long notificationowner) {
		this.notificationowner = notificationowner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	


}
