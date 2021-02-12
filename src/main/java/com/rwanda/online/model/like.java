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
@Table(name = "likes")
public class like {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="like_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private  User userLiked;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accomodation_id")
	private  User userAccomodated;
	public like() {
		super();
	}
	public like(User userLiked, User userAccomodated) {
		super();
		this.userLiked = userLiked;
		this.userAccomodated = userAccomodated;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUserLiked() {
		return userLiked;
	}
	public void setUserLiked(User userLiked) {
		this.userLiked = userLiked;
	}
	public User getUserAccomodated() {
		return userAccomodated;
	}
	public void setUserAccomodated(User userAccomodated) {
		this.userAccomodated = userAccomodated;
	}
	
	
	

}
