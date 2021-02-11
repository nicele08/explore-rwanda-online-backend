package com.rwanda.online.controller;


import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rwanda.online.exception.ResourceNotFoundException;
import com.rwanda.online.model.Trip;
import com.rwanda.online.repository.AccomodationRepository;
import com.rwanda.online.repository.LocationRepository;
import com.rwanda.online.repository.TripRepository;
import com.rwanda.online.repository.UserRepository;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RestController
@RequestMapping("/api/v1/")
public class TripController {
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@GetMapping("/locations/{locationId}/trips")
	public List<Trip> getTripsByLocation(@PathVariable(value="locationId") Long locationId){
		return tripRepository.findByLocationId(locationId);
	}
	
	@PostMapping("users/{userId}/locations/{locationId}/accomodations/{accomodationId}/trips")
    public Trip createTrip(@PathVariable(value = "locationId") Long locationId,
    		@PathVariable(value = "userId") Long userId,
    		@PathVariable(value = "accomodationId") Long accomodationId,
        @Valid @RequestBody Trip trip) throws ResourceNotFoundException {
		if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("requester not found");
        }
		if (!accomodationRepository.existsById(accomodationId)) {
            throw new ResourceNotFoundException("requester not found");
        }
        return locationRepository.findById(locationId).map(location -> {
        	if(location.getId() == locationId) {
        		trip.setLocation(location);
            	trip.setAccomodation(accomodationRepository.getOne(accomodationId));
            	trip.setRequester(userRepository.getOne(userId));
            	return tripRepository.save(trip);
        	} else {
        		return null;
        	}
        }).orElseThrow(() -> new ResourceNotFoundException("location not found"));
    }
	
	@PutMapping("/users/{userId}/locations/{locationId}/accomodations/{accomodationId}/trips/{tripId}")
    public Trip updateTrip(@PathVariable(value = "locationId") Long locationId,
    		@PathVariable(value = "accomodationId") Long accomodationId,
    		@PathVariable(value = "userId") Long userId,
        @PathVariable(value = "tripId") Long tripId, @Valid @RequestBody Trip tripDetails)
    throws ResourceNotFoundException {
		if( !userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("requester not found");
		}
        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException("location not found");
        }
        
        if (!accomodationRepository.existsById(accomodationId)) {
        	throw new ResourceNotFoundException("accomodation not found");
        }
        

        return tripRepository.findById(tripId).map(trip -> {
        	trip.setRequester(userRepository.getOne(userId));
            trip.setTravelType(tripDetails.getTravelType());
            trip.setAccomodation(accomodationRepository.getOne(accomodationId));
            trip.setTravelReason(tripDetails.getTravelReason());
            trip.setLocation(locationRepository.getOne(locationId));
            trip.setRequestStatus(tripDetails.getRequestStatus());
            return tripRepository.save(trip);
        }).orElseThrow(() -> new ResourceNotFoundException("trip id not found"));
    }
	
	@DeleteMapping("/locations/{locationId}/trips/{tripId}")
    public ResponseEntity < ? > deleteTrip(@PathVariable(value = "locationId") Long locationId,
        @PathVariable(value = "tripId") Long tripId) throws ResourceNotFoundException {
        return tripRepository.findByIdAndLocationId(tripId, locationId).map(trip -> {
            tripRepository.delete(trip);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "trip not found with id " + tripId + " and locationId " + locationId));
    }
	
	@GetMapping("/trips/{tripId}")
	public ResponseEntity<Trip> getRoom(@PathVariable(value="tripId") Long tripId) {
		Trip trip = tripRepository.findById(tripId)
				.orElseThrow(() -> new ResourceNotFoundException("Trip not found :: " + tripId));
		return ResponseEntity.ok(trip);
	}

}
