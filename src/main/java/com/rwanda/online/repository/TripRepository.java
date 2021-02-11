package com.rwanda.online.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rwanda.online.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	List<Trip> findByLocationId(Long locationId);
	Optional<Trip> findByIdAndLocationId(Long id, Long locationId);
}
