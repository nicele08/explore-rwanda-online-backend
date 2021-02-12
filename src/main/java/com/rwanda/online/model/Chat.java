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
@Table(name = "chats")
public class Chat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="chat_id")
	private Long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private  User userChatted1;
	
	@Column(name = "message", nullable = false, length = 20)
	private String message;

	public Chat() {
		super();
	}

	public Chat(User userChatted1, String message) {
		super();
		this.userChatted1 = userChatted1;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserChatted1() {
		return userChatted1;
	}

	public void setUserChatted1(User userChatted1) {
		this.userChatted1 = userChatted1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
