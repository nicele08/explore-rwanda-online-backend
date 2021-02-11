package com.rwanda.online.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comment")

public class Comment extends AuditModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private  Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="user_id", nullable=true)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="trip_id", nullable=false)
	@JsonIgnore
	private Trip trip;
	
	@Column(name = "comment", nullable = false, length = 300)
	String comment;

	public Comment() {
		super();
	}

	public Comment(User user, Trip trip, String comment) {
		super();
		this.user = user;
		this.trip = trip;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
