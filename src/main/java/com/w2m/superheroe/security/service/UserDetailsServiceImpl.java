package com.w2m.superheroe.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "USER");
		} else if ("manager".equals(username)) {
			return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "MANAGER");
		} else if ("admin".equals(username)) {
			return this.userBuilder(username, new BCryptPasswordEncoder().encode("12345"), "USER", "MANAGER", "ADMIN");
		} else {
			throw new UsernameNotFoundException("Usuario no Encontrado");
		}
    }
	
	private User userBuilder(String username, String password, String... roles) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
