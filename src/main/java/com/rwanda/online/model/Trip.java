package com.rwanda.online.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trip")
public class Trip extends AuditModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trip_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="requester_id", nullable=false)
	@JsonIgnore
	private User requester;
	
	@Column(name = "request_status", nullable = false, length = 20)
	private String requestStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="location_id", nullable=false)
	@JsonIgnore
	private Location location;
	
	@Column(name = "travel_type", nullable = false, length = 64)
	private String travelType;
	
	@Column(name = "travel_reason", nullable = false, length = 300)
	private String travelReason;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="accomodation_id", nullable = false)
	@JsonIgnore
	private Accomodation accomodation;
	
	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comment> comments;
	
	public Trip() {
		super();
	}

	

	public Trip(User user, String requestStatus, Location location, String travelType, String travelReason,
			Accomodation accomodation, List<Comment> comments) {
		super();
		this.requester = user;
		this.requestStatus = requestStatus;
		this.location = location;
		this.travelType = travelType;
		this.travelReason = travelReason;
		this.accomodation = accomodation;
		this.comments = comments;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getRequester() {
		return requester;
	}



	public void setRequester(User requester) {
		this.requester = requester;
	}



	public String getRequestStatus() {
		return requestStatus;
	}



	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}



	public Location getLocation() {
		return location;
	}



	public void setLocation(Location location) {
		this.location = location;
	}



	public String getTravelType() {
		return travelType;
	}



	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}



	public String getTravelReason() {
		return travelReason;
	}



	public void setTravelReason(String travelReason) {
		this.travelReason = travelReason;
	}



	public Accomodation getAccomodation() {
		return accomodation;
	}



	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	
}
