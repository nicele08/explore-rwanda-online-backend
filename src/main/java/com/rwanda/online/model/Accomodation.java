package com.rwanda.online.model;

import javax.persistence.CascadeType;
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
@Table(name="accomodation")
public class Accomodation extends AuditModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="host_id")
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false, length = 64)
	private String name;
	
	@Column(name = "description", nullable = false, length = 600)
	private String description;
	
	@Column(name = "longitude", nullable = false, length = 200)
	private String longitude;
	
	@Column(name = "images", nullable = false, length = 200)
	private String[] images;
	
	@Column(name = "facilities", nullable = false, length = 200)
	private String[] facilities;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name="location_id", nullable=false)
	@JsonIgnore
	private Location location;

	public Accomodation() {
		super();
	}

	public Accomodation(String name, String description, String longitude, String[] images, String[] facilities,
			Location location) {
		super();
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.images = images;
		this.facilities = facilities;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String[] getFacilities() {
		return facilities;
	}

	public void setFacilities(String[] facilities) {
		this.facilities = facilities;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
