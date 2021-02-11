package com.rwanda.online.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rwanda.online.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByTripId(Long tripId);
	Optional<Comment> findByIdAndTripId(Long id, Long tripId);
}
