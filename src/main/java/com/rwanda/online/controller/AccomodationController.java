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

import com.rwanda.online.exception.ResourceNotFoundException;
import com.rwanda.online.model.Accomodation;
import com.rwanda.online.repository.AccomodationRepository;
import com.rwanda.online.repository.LocationRepository;

@RestController
@RequestMapping("/api/v1/")
public class AccomodationController {
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping("/locations/{locationId}/accomodations")
	public List<Accomodation> getAccomodationsByLocation(@PathVariable(value="locationId") Long locationId){
		return accomodationRepository.findByLocationId(locationId);
	}
	
	@PostMapping("/locations/{locationId}/accomodations")
    public Accomodation createAccomodation(@PathVariable(value = "locationId") Long locationId,
        @Valid @RequestBody Accomodation accomodation) throws ResourceNotFoundException {
        return locationRepository.findById(locationId).map(location -> {
        	accomodation.setLocation(location);
            return accomodationRepository.save(accomodation);           
        }).orElseThrow(() -> new ResourceNotFoundException("location not found"));
    }
	
	@PutMapping("/locations/{locationId}/accomodation/{accomodationId}")
    public Accomodation updateAccomodation(@PathVariable(value = "locationId") Long locationId,
        @PathVariable(value = "accomodationId") Long accomodationId, @Valid @RequestBody Accomodation accomodationDetails)
    throws ResourceNotFoundException {
        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException("instructorId not found");
        }

        return accomodationRepository.findById(accomodationId).map(accomodation -> {
            accomodation.setName(accomodationDetails.getName());
            accomodation.setDescription(accomodation.getDescription());
            accomodation.setFacilities(accomodation.getFacilities());
            accomodation.setImages(accomodationDetails.getImages());
            accomodation.setLongitude(accomodationDetails.getLongitude());
            return accomodationRepository.save(accomodation);
        }).orElseThrow(() -> new ResourceNotFoundException("course id not found"));
    }
	
	@DeleteMapping("/locaitons/{locationId}/accomodation/{accomodationId}")
    public ResponseEntity < ? > deleteAccomodation(@PathVariable(value = "locationId") Long locationId,
        @PathVariable(value = "accomodationId") Long accomodationId) throws ResourceNotFoundException {
        return accomodationRepository.findByIdAndLocationId(accomodationId, locationId).map(accomodation -> {
            accomodationRepository.delete(accomodation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Accomodation not found with id " + accomodationId + " and locationId " + locationId));
    }

}
