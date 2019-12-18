package com.cognizant.signup.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cognizant.signup.SignupApplication;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupApplication.class);
    

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			LOGGER.info("Start");
			httpSecurity.cors();
			httpSecurity.csrf()
						.disable()
						.httpBasic()
						.and()
						.authorizeRequests()
						.antMatchers("/signup/sign-up").anonymous();
			LOGGER.info("End");
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			LOGGER.info("Start");
			LOGGER.info("End");
			return new BCryptPasswordEncoder();
		}
	}
