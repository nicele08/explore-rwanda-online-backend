package com.rwanda.online.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rwanda.online.exception.ResourceNotFoundException;
import com.rwanda.online.model.Comment;
import com.rwanda.online.repository.TripRepository;
import com.rwanda.online.repository.UserRepository;
import com.rwanda.online.repository.CommentRepository;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TripRepository tripRepository;
	
	@GetMapping("/trips/{tripId}/comments")
	public List<Comment> getcommentsBytrip(HttpServletRequest request, @PathVariable(value="tripId") Long tripId){
		return commentRepository.findByTripId(tripId);
	}
	
	@PostMapping("/trips/{tripId}/comments")
    public Comment createcomment(HttpServletRequest request, @PathVariable(value = "tripId") Long tripId,
        @Valid @RequestBody Comment comment) throws ResourceNotFoundException {
		
		Long userId = (Long) request.getAttribute("userId");
		
		if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user not found");
        }
        return tripRepository.findById(tripId).map(trip -> {
        	comment.setTrip(trip);
        	comment.setUser(userRepository.getOne(userId));
        	return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("trip not found"));
    }
	
	@PutMapping("/trips/{tripId}/comments/{commentId}")
    public Comment updatecomment(HttpServletRequest request, @PathVariable(value = "tripId") Long tripId,
        @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody Comment commentDetails)
    throws ResourceNotFoundException {
        if (!tripRepository.existsById(tripId)) {
            throw new ResourceNotFoundException("trip not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
        	comment.setComment(commentDetails.getComment());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("comment id not found"));
    }
	
	@DeleteMapping("/trips/{tripId}/comments/{commentId}")
    public ResponseEntity < ? > deletecomment(HttpServletRequest request, @PathVariable(value = "tripId") Long tripId,
        @PathVariable(value = "commentId") Long commentId) throws ResourceNotFoundException {
        return commentRepository.findByIdAndTripId(commentId, tripId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "comment not found with id " + commentId + " and tripId " + tripId));
    }
	
	@GetMapping("/comments/{commentId}")
	public ResponseEntity<Comment> getRoom(HttpServletRequest request, @PathVariable(value="commentId") Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment not found :: " + commentId));
		return ResponseEntity.ok(comment);
	}
}
