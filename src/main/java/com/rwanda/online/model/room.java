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
@Table(name = "rooms")
public class room {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long id;
	
	@Column(name = "type", nullable = false, length = 20)
	private String type;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "accomodation_id")
	private Long accomodation_id;
	
	private String images;
	

}
