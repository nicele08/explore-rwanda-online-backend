package com.rwanda.online.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "trip_id")
	private  Trip userTrip1;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private  User userCommented1;
	
	@Column(name = "comment", nullable = false, length = 20)
	private String comment;

	public Comment() {
		super();
	}

	public Comment(Trip userTrip1, User userCommented1, String comment) {
		super();
		this.userTrip1 = userTrip1;
		this.userCommented1 = userCommented1;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Trip getUserTrip1() {
		return userTrip1;
	}

	public void setUserTrip1(Trip userTrip1) {
		this.userTrip1 = userTrip1;
	}

	public User getUserCommented1() {
		return userCommented1;
	}

	public void setUserCommented1(User userCommented1) {
		this.userCommented1 = userCommented1;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
