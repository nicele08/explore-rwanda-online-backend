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
	@Column(name="preferences_id")
	private  Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requester_id")
	private Trip requester;
	
	@Column(name = "enabled")
	private boolean emailnotifiation;
	@Column(name = "enabled")
	private boolean appnotifiation;
	

}
