package com.rwanda.online.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "location")
public class Location extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="location_id")
	private Long id;
	
	@Column(name = "district", nullable = false, unique = true, length = 64)
	private String district;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List < Accomodation > accomodations;
	
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Trip> trips;
	
	public Location() {
		super();
	}
	
	

	public Location(String district, List<Accomodation> accomodations, List<Trip> trips) {
		super();
		this.district = district;
		this.accomodations = accomodations;
		this.trips = trips;
	}



	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setAccomodations(List<Accomodation> accomodations) {
		this.accomodations = accomodations;
	}

	public List<Accomodation> getAccomodations() {
		return accomodations;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
