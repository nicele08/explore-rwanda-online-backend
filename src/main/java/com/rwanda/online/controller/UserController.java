package com.rwanda.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rwanda.online.model.User;
import com.rwanda.online.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository employeeRepository;
	
	// get all employees
	@GetMapping("/users")
	public List<User> getAllEmployees() {
		return employeeRepository.findAll();
	}
}
