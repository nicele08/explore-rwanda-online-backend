package com.rwanda.online.repository;

import org.springframework.stereotype.Repository;

import com.rwanda.online.model.Accomodation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>  {
	List<Accomodation> findByLocationId(Long locationId);
	Optional<Accomodation> findByIdAndLocationId(Long id, Long locationId);
}
