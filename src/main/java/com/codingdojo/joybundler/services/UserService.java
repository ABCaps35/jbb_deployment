package com.codingdojo.joybundler.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.joybundler.models.LoginUser;
import com.codingdojo.joybundler.models.User;
import com.codingdojo.joybundler.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User b) {
		return userRepository.save(b);
	}
	
	public User findUser(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		if(maybeUser.isPresent()) {
			return maybeUser.get();
		}
		else {
			return null;
		}
	}
	
	public User findUser(String email) {
		Optional<User> maybeUser = userRepository.findByEmail(email);
		if(maybeUser.isPresent()) {
			return maybeUser.get();
		} else {
			return null;
		}
	}
	
	public User updateUser(Long id, User userIn) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setId(id);
			user.setName(userIn.getName());
			user.setEmail(userIn.getEmail());
			return userRepository.save(user);
		} else {
			return null;
		}
	}
	
	public void deleteUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			userRepository.delete(optionalUser.get());
		}
	}
	
    public User register(User newUser, BindingResult result) {
    	//Validate: New user email is NOT already used
    	Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
    	if(potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email address is already in use");
    	}
    	
    	//Validate: Password confirmation matches password
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "Confirm Password must match Password");
    	}
    	
    	//Submit and return new user if no errors thrown
    	if(result.hasErrors()) {
    	    return null;
    	}
    	else {
    		//BCrypt hash password
        	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        	newUser.setPassword(hashed);
        	
        	//Submit new user
        	userRepository.save(newUser);
        	return newUser;
    	}
    }
    
    public User login(LoginUser newLoginUser, BindingResult result) {
    	//Validate: User email IS in use
    	Optional<User> potentialUser = userRepository.findByEmail(newLoginUser.getEmail());
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User does not exist");
    	}
        //Validate: User password matches one on file
    	else if(!BCrypt.checkpw(newLoginUser.getPassword(), potentialUser.get().getPassword())) {
    	    result.rejectValue("password", "Matches", "Incorrect Password");
    	}
    	
    	//Return existing user if no errors thrown
    	if(result.hasErrors()) {
    	    return null;
    	}
    	else {
    		return potentialUser.get();
    	}
    }
}
