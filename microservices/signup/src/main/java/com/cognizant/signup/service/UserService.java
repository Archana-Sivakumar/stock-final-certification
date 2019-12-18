package com.cognizant.signup.service;

import org.springframework.stereotype.Service;
import com.cognizant.signup.exception.UserAlreadyExistsException;
import com.cognizant.signup.model.Users;

@Service
public interface UserService {

	Users getAllUser(int id);

	public void userSignUp(Users user) throws UserAlreadyExistsException;
}
