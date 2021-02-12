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
import com.rwanda.online.model.Room;
import com.rwanda.online.repository.AccomodationRepository;
import com.rwanda.online.repository.RoomRepository;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {
	
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@GetMapping("/accomodations/{accomodationId}/rooms")
	public List<Room> getRoomsByAccomodation(HttpServletRequest request, @PathVariable(value="accomodationId") Long accomodationId){
		return roomRepository.findByAccomodationId(accomodationId);
	}
	
	@PostMapping("/accomodations/{accomodationId}/rooms")
    public Room createRoom(HttpServletRequest request, @PathVariable(value = "accomodationId") Long accomodationId,
        @Valid @RequestBody Room room) throws ResourceNotFoundException {
        return accomodationRepository.findById(accomodationId).map(accomodation -> {
        	room.setAccomodation(accomodation); 
        	return roomRepository.save(room);
        }).orElseThrow(() -> new ResourceNotFoundException("accomodation not found"));
    }
	
	@PutMapping("/accomodations/{accomodationId}/rooms/{roomId}")
    public Room updateroom(HttpServletRequest request, @PathVariable(value = "accomodationId") Long accomodationId,
        @PathVariable(value = "roomId") Long roomId, @Valid @RequestBody Room roomDetails)
    throws ResourceNotFoundException {
        if (!accomodationRepository.existsById(accomodationId)) {
            throw new ResourceNotFoundException("accomodation not found");
        }

        return roomRepository.findById(roomId).map(room -> {
        	room.setName(roomDetails.getName());
        	room.setImages(roomDetails.getImages());
        	room.setPrice(roomDetails.getPrice());
        	room.setType(roomDetails.getType());
            return roomRepository.save(room);
        }).orElseThrow(() -> new ResourceNotFoundException("room id not found"));
    }
	
	@DeleteMapping("/accomodations/{accomodationId}/rooms/{roomId}")
    public ResponseEntity < ? > deleteroom(HttpServletRequest request, @PathVariable(value = "accomodationId") Long accomodationId,
        @PathVariable(value = "roomId") Long roomId) throws ResourceNotFoundException {
        return roomRepository.findByIdAndAccomodationId(roomId, accomodationId).map(room -> {
            roomRepository.delete(room);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "room not found with id " + roomId + " and accomodationId " + accomodationId));
    }
	
	@GetMapping("/rooms/{roomId}")
	public ResponseEntity<Room> getRoom(HttpServletRequest request, @PathVariable(value="roomId") Long roomId) {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found :: " + roomId));
		return ResponseEntity.ok(room);
	}


}
