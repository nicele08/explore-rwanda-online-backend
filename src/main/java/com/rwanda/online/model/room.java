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
@Table(name = "room")
public class Room extends AuditModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false, length = 20)
	private String name;
	
	@Column(name = "type", nullable = false, length = 20)
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="accomodation_id", nullable=false)
	@JsonIgnore
	private Accomodation accomodation;
	
	@Column(name = "images", nullable = false, length = 200)
	private String[] images;
	
	@Column(name="price", nullable=false)
	private Double price;

	public Room() {
		super();
	}
	
	public Room(String type, Accomodation accomodation, String[] images, Double price) {
		super();
		this.type = type;
		this.accomodation = accomodation;
		this.images = images;
		this.price = price;
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

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
