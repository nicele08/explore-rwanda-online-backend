package com.rwanda.online.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rwanda.online.constants.Constants;
import com.rwanda.online.exception.ResourceNotFoundException;
import com.rwanda.online.model.ConfirmationToken;
import com.rwanda.online.model.User;
import com.rwanda.online.repository.ConfirmationTokenRepository;
import com.rwanda.online.repository.UserRepository;
import com.rwanda.online.service.EmailSenderService;
import com.rwanda.online.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
    private EmailSenderService emailSenderService;
	
	@Autowired
	UserService userService;
	
	// get all users
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String, Object> userMap) {
		String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.putAll(generateJWTToken(user));
        return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody User user) {	
		User userCreated = userService.registerUser(user);
		ConfirmationToken confirmationToken = new ConfirmationToken(userCreated);
		
		confirmationTokenRepository.save(confirmationToken);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("explore.rwanda.online@gmail.com");
		mailMessage.setText("To confirm your account, please click here: "
				+ "http://localhost:9090/confirm-account?token="+confirmationToken.getConfirmationToken());
		emailSenderService.sendEmail(mailMessage);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", userCreated);
		data.putAll(generateJWTToken(userCreated));
		data.put("message", "Dear " + userCreated.getFirstName() + ", check verfication link to your e-mail to complete registration.");
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/confirm-account",  method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Map<String, String>> confirmUserAccount(@RequestParam("token")String confirmationToken){
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		Map<String, String> map = new HashMap<>();
		if(token != null) {
			User user = userRepository.findByEmailAddress(token.getUser().getEmail());
			user.setEnabled(true);
			map = generateJWTToken(user);
			map.put("message", "Account verified");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.put("message", "The link is invalid or broken!");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist whith id: " + id));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist whith id: " + id));
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(user.getEmail());
		user.setPassword(userDetails.getPassword());
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist whith id: " + id));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("enabled", user.isEnabled())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
