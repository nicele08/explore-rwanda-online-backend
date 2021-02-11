package com.rwanda.online.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rwanda.online.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByAccomodationId(Long locationId);
	Optional<Room> findByIdAndAccomodationId(Long id, Long accomodationId);
}
