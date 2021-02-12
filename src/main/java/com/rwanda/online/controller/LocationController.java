package com.rwanda.online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rwanda.online.model.Location;
import com.rwanda.online.repository.LocationRepository;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping("/locations")
	public List <Location> getLocations() {
		return locationRepository.findAll();
	}
	
	@GetMapping("locations/{id}")
	public ResponseEntity <Location> getLocationById(HttpServletRequest request,
			@PathVariable(value = "id") Long locationId) throws ResourceNotFoundException{
		
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found :: " + locationId));
		return ResponseEntity.ok().body(location);
	}
	
	@PostMapping("/locations")
	public Location createLocation(HttpServletRequest request, @Valid @RequestBody Location location) {
		return locationRepository.save(location);
	}
	
	@PutMapping("/locations/{id}") 
	public ResponseEntity <Location> updateLocation(HttpServletRequest request, @PathVariable(value="id") Long locationId,
			@Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found :: " + locationId));
		location.setDistrict(locationDetails.getDistrict());
		final Location updatedLocation = locationRepository.save(location);
		return ResponseEntity.ok(updatedLocation);
	}
	
	@DeleteMapping("/locations/{id}")
	public Map<String, Boolean> deleteLocation(HttpServletRequest request,
			@PathVariable(value="id") Long locationId) throws ResourceNotFoundException{
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found :: " + locationId));
		locationRepository.delete(location);
		Map<String, Boolean> response = new HashMap<> ();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
