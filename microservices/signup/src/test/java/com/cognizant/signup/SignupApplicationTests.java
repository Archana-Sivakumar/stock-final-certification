package com.cognizant.signup;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cognizant.signup.exception.UserAlreadyExistsException;
import com.cognizant.signup.model.Users;
import com.cognizant.signup.repository.UserRepository;
import com.cognizant.signup.security.AppUserDetailsService;
import com.cognizant.signup.service.UserService;


@SpringBootTest
class SignupApplicationTests {
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
    UserService userService;
	
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testvoidSignupPatient() {

		
		Users user = createUser();
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(null);
		assertDoesNotThrow(() -> userService.userSignUp(user));
		
	}

	
	
	@Test
	public void patientUserAlreadyExistsException() throws UserAlreadyExistsException {
		
		Users user = createUser();
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		assertThrows(UserAlreadyExistsException.class,()->userService.userSignUp(user));
			
	}
	
	
	public Users createUser() {
	   
		Users newUser = new Users();
		newUser.setUserName("user");
		newUser.setPassword(bCryptPasswordEncoder.encode("user"));
//		Role role = new Role(1, "USER");
//		newUser.setRole(role);
		return newUser;
	}

}
