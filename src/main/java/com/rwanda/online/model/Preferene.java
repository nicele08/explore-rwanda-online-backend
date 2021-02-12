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
@Table(name = "preferences")

public class Preferene {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="preference_id")
	private  Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requester_id")
	private Trip requester;
	
	@Column(name = "enabled")
	private boolean emailnotifiation;
	@Column(name = "enabled")
	private boolean appnotifiation;
	public Preferene() {
		super();
	}
	public Preferene(Trip requester, boolean emailnotifiation, boolean appnotifiation) {
		super();
		this.requester = requester;
		this.emailnotifiation = emailnotifiation;
		this.appnotifiation = appnotifiation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Trip getRequester() {
		return requester;
	}
	public void setRequester(Trip requester) {
		this.requester = requester;
	}
	public boolean isEmailnotifiation() {
		return emailnotifiation;
	}
	public void setEmailnotifiation(boolean emailnotifiation) {
		this.emailnotifiation = emailnotifiation;
	}
	public boolean isAppnotifiation() {
		return appnotifiation;
	}
	public void setAppnotifiation(boolean appnotifiation) {
		this.appnotifiation = appnotifiation;
	}
	
	

}
