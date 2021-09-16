package com.w2m.superheroe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
class AuthenticationTests {
	
	@Autowired
    AuthenticationManager authenticationManager;

	@Test
	void authenticatedUser() {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken("user", "12345"));
		assertTrue(authentication.isAuthenticated());
	}
	
	@Test
	void authenticatedManager() {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken("manager", "12345"));
		assertTrue(authentication.isAuthenticated());
	}
	
	@Test
	void authenticatedAdmin() {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken("admin", "12345"));
		assertTrue(authentication.isAuthenticated());
	}

}
