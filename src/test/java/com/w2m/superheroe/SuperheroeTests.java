package com.w2m.superheroe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.w2m.superheroe.entity.Superheroe;
import com.w2m.superheroe.security.jwt.JwtProvider;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class SuperheroeTests {
	
	@Autowired
    private WebTestClient webTestClient;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Test
    void testSuperheroesById() {
		String token = getToken("user", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .get().uri("v1/superheroes/{id}", 1)
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesAll() {
		String token = getToken("user", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .get().uri("v1/superheroes")
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesLikeName() {
		String token = getToken("user", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .get().uri("v1/superheroes/search?name=man")
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesLikeNameIsBlank() {
		String token = getToken("user", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .get().uri("v1/superheroes/search?name")
                .exchange()
                .expectStatus().isNoContent();
    }
	
	@Test
    void testSuperheroesCreate() {
		String token = getToken("manager", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .post().uri("v1/superheroes")
                .bodyValue(getSuperheroe())
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesUpdate() {
		String token = getToken("manager", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .put().uri("v1/superheroes/{id}", 1)
                .bodyValue(getSuperheroeUpdate())
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesDelete() {
		String token = getToken("admin", "12345");
		
        this.webTestClient
                .mutate().defaultHeader("Authorization", "Bearer " + token).build()
                .delete().uri("v1/superheroes/{id}", 5)
                .exchange()
                .expectStatus().isOk();
    }
	
	public String getToken(String user, String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user, password));
		return this.jwtProvider.generateToken(authentication);
	}
	
	public Superheroe getSuperheroe() {
		Superheroe superheroe = new Superheroe();
		superheroe.setId(Long.valueOf(9));
		superheroe.setName("Doctor Strange");
		return superheroe;
	}
	
	public Superheroe getSuperheroeUpdate() {
		Superheroe superheroe = new Superheroe();
		superheroe.setName("Doctor Strange");
		return superheroe;
	}

}
