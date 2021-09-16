package com.w2m.superheroe;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.w2m.superheroe.security.dto.LoginUsuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthLoginTests {
	
	@Autowired
    private WebTestClient webTestClient;
	
	@Test
    void testAuthLoginUnauthorized() {
        this.webTestClient
                .mutate().filter(basicAuthentication("user", "342456")).build()
                .post().uri("/auth/login")
                .bodyValue(new LoginUsuario("user", "342456"))
                .exchange()
                .expectStatus().isUnauthorized();
    }
	
	@Test
    void testAuthLoginUser() {
        this.webTestClient
                .mutate().filter(basicAuthentication("user", "12345")).build()
                .post().uri("/auth/login")
                .bodyValue(new LoginUsuario("user", "12345"))
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testAuthLoginManager() {
        this.webTestClient
                .mutate().filter(basicAuthentication("manager", "12345")).build()
                .post().uri("/auth/login")
                .bodyValue(new LoginUsuario("user", "12345"))
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testAuthLoginAdmin() {
        this.webTestClient
                .mutate().filter(basicAuthentication("admin", "12345")).build()
                .post().uri("/auth/login")
                .bodyValue(new LoginUsuario("user", "12345"))
                .exchange()
                .expectStatus().isOk();
    }

}
