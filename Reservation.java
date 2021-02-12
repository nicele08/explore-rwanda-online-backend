package com.rwanda.online.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requester_id")
	private User userRequester;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "trip_id")
	private Trip userTrip;
	
	
	private Long accomodation_id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private Room userRoom;

	public Reservation() {
		super();
	}

	public Reservation(User userRequester, Trip userTrip, Room userRoom) {
		super();
		this.userRequester = userRequester;
		this.userTrip = userTrip;
		this.userRoom = userRoom;
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

	public Trip getUserTrip() {
		return userTrip;
	}

	public void setUserTrip(Trip userTrip) {
		this.userTrip = userTrip;
	}

	public Room getUserRoom() {
		return userRoom;
	}

	public void setUserRoom(Room userRoom) {
		this.userRoom = userRoom;
	}
	
	
	
}
