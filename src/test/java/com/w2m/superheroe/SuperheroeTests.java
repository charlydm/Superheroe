package com.w2m.superheroe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${authentication.user.username}")
    private String username;
	
	@Value("${authentication.user.password}")
    private String user_password;
	
	@Value("${authentication.manager.username}")
    private String managername;
	
	@Value("${authentication.manager.password}")
    private String manager_password;
	
	@Value("${authentication.admin.username}")
    private String adminname;
	
	@Value("${authentication.admin.password}")
    private String admin_password;
	
	@Test
    void testSuperheroesById() {
		this.webTestClient
				.mutate().defaultHeader("Authorization", getToken(username, user_password)).build()
                .get().uri("v1/superheroes/{id}", 1)
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesAll() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(username, user_password)).build()
                .get().uri("v1/superheroes")
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesLikeName() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(username, user_password)).build()
                .get().uri("v1/superheroes/search?name=man")
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesLikeNameIsBlank() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(username, user_password)).build()
                .get().uri("v1/superheroes/search?name")
                .exchange()
                .expectStatus().isNoContent();
    }
	
	@Test
    void testSuperheroesCreate() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(managername, manager_password)).build()
                .post().uri("v1/superheroes")
                .bodyValue(getSuperheroe())
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesUpdate() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(managername, manager_password)).build()
                .put().uri("v1/superheroes/{id}", 1)
                .bodyValue(getSuperheroeUpdate())
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesDelete() {
        this.webTestClient
                .mutate().defaultHeader("Authorization", getToken(adminname, admin_password)).build()
                .delete().uri("v1/superheroes/{id}", 5)
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testSuperheroesOrder() {
		this.webTestClient
				.mutate().defaultHeader("Authorization", getToken(username, user_password)).build()
                .get().uri("v1/superheroes/view/order")
                .exchange()
                .expectStatus().isOk();
    }
	
	public String getToken(String user, String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user, password));
		StringBuilder bearerToken = new StringBuilder();
		bearerToken.append("Bearer ").append(jwtProvider.generateToken(authentication));
		return bearerToken.toString();
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
