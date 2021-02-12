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
public class Room {
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

	public Room() {
		super();
	}

	public Room(String type, Long accomodation_id, String images) {
		super();
		this.type = type;
		this.accomodation_id = accomodation_id;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAccomodation_id() {
		return accomodation_id;
	}

	public void setAccomodation_id(Long accomodation_id) {
		this.accomodation_id = accomodation_id;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
	

}
