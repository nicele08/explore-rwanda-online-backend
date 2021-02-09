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
@Table(name = "trips")
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requester_id")
	private User userRequester;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "manager_id")
	private User userManager;
	
	@Column(name = "request_status", nullable = false, length = 20)
	private String request_status;
	
	
	private Long from;
	private Long to;
	
	@Column(name = "travel_type", nullable = false, length = 64)
	private String travel_type;
	
	@Column(name = "travel_reason", nullable = false, length = 64)
	private String travel_reason;
	
	private Long accomodation;
	
	

	public Trip() {
		super();
	}



	public Trip(User userRequester, User userManager, String request_status, String travel_type, String travel_reason) {
		super();
		this.userRequester = userRequester;
		this.userManager = userManager;
		this.request_status = request_status;
		this.travel_type = travel_type;
		this.travel_reason = travel_reason;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserRequester() {
		return userRequester;
	}

	public void setUserRequester(User userRequester) {
		this.userRequester = userRequester;
	}

	public User getUserManager() {
		return userManager;
	}

	public void setUserManager(User userManager) {
		this.userManager = userManager;
	}



	public String getRequest_status() {
		return request_status;
	}



	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}



	public String getTravel_type() {
		return travel_type;
	}



	public void setTravel_type(String travel_type) {
		this.travel_type = travel_type;
	}



	public String getTravel_reason() {
		return travel_reason;
	}



	public void setTravel_reason(String travel_reason) {
		this.travel_reason = travel_reason;
	}
	
	
}
