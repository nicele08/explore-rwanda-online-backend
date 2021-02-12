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
@Table(name = "feedbacks")
public class Feedback {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="feedback_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private  User userLiked1;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accomodation_id")
	private  User userAccomodated1;
	
	@Column(name = "message", nullable = false, length = 20)
	private String message;

	public Feedback() {
		super();
	}

	public Feedback(User userLiked1, User userAccomodated1, String message) {
		super();
		this.userLiked1 = userLiked1;
		this.userAccomodated1 = userAccomodated1;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserLiked1() {
		return userLiked1;
	}

	public void setUserLiked1(User userLiked1) {
		this.userLiked1 = userLiked1;
	}

	public User getUserAccomodated1() {
		return userAccomodated1;
	}

	public void setUserAccomodated1(User userAccomodated1) {
		this.userAccomodated1 = userAccomodated1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
