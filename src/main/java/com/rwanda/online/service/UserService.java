package com.rwanda.online.service;

import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rwanda.online.exception.AuthException;
import com.rwanda.online.model.User;
import com.rwanda.online.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User validateUser(String email, String password) throws AuthException {
        if(email != null) email = email.toLowerCase();        
        try {
        	User userFound =  userRepository.findByEmailAddress(email);
            if (!BCrypt.checkpw(password, userFound.getPassword()))
            	throw new AuthException("Invalid email or password");
            return userFound;
        } catch(Exception e) {
        	throw new AuthException("Invalid email or password");
        }
    }
	
	public User registerUser(User user) throws AuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String email = user.getEmail();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
		user.setPassword(hashedPassword);
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new AuthException("Invalid email format");
        long count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new AuthException("Email already in use");
        return userRepository.save(user);
    }
}
