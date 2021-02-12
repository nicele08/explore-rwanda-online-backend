package com.rwanda.online.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ratings")
public class Ratings {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rating_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User requester1;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "acccomodation_id")
	private User requester2;

	@Column(name = "request_status", nullable = false, length = 20)

	private String rating;

	public Ratings() {
		super();
	}

	public Ratings(User requester1, User requester2, String rating) {
		super();
		this.requester1 = requester1;
		this.requester2 = requester2;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRequester1() {
		return requester1;
	}

	public void setRequester1(User requester1) {
		this.requester1 = requester1;
	}

	public User getRequester2() {
		return requester2;
	}

	public void setRequester2(User requester2) {
		this.requester2 = requester2;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	

}
