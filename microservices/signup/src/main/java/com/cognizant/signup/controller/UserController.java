package com.cognizant.signup.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.signup.SignupApplication;
import com.cognizant.signup.exception.UserAlreadyExistsException;
import com.cognizant.signup.model.Users;
import com.cognizant.signup.service.ConfirmationService;
import com.cognizant.signup.service.EmailService;
import com.cognizant.signup.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("") 
public class UserController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupApplication.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ConfirmationService confirmationService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/user/{id}")
	public Users getAllUser(@PathVariable int id) {
		
		return userService.getAllUser(id);
	}
	
	@PostMapping("/sign-up")
	public void userSignup(@RequestBody @Valid Users user) throws UserAlreadyExistsException{
		
		LOGGER.info("Start");
		
		userService.userSignUp(user);
		String token = confirmationService.setTokenForConfirmation(user.getUserName());
		emailService.send("ctstestmail10@gmail.com", user.getEmail(), "Confirmation", "http://localhost:8083/authentication-service/users/confirmation/"+token);
		
		LOGGER.info("End");
	}
	
	@GetMapping("/confirmation/{token}")
	public void confirmation(@PathVariable String token) {
		confirmationService.confirmMailAddress(token);
	}
	
	public PasswordEncoder passwordEncoder() {
		
	     LOGGER.info("Start");
	     return new BCryptPasswordEncoder();
	        
	}
	
}
