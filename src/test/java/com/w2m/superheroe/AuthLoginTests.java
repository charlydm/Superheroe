package com.w2m.superheroe;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.w2m.superheroe.security.dto.Login;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthLoginTests {
	
	@Autowired
    private WebTestClient webTestClient;
	
	private static String fail_password = "342456";
	
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
    void testAuthLoginUser() {
        this.webTestClient
                .mutate().filter(basicAuthentication(username, user_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(username, user_password))
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testAuthLoginUserUnauthorized() {
        this.webTestClient
                .mutate().filter(basicAuthentication(username, fail_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(username, "342456"))
                .exchange()
                .expectStatus().isUnauthorized();
    }
	
	@Test
    void testAuthLoginManager() {
        this.webTestClient
                .mutate().filter(basicAuthentication(managername, manager_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(managername, manager_password))
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testAuthLoginManagerUnauthorized() {
        this.webTestClient
                .mutate().filter(basicAuthentication(managername, fail_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(managername, "342456"))
                .exchange()
                .expectStatus().isUnauthorized();
    }
	
	@Test
    void testAuthLoginAdmin() {
        this.webTestClient
                .mutate().filter(basicAuthentication(adminname, admin_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(adminname, admin_password))
                .exchange()
                .expectStatus().isOk();
    }
	
	@Test
    void testAuthLoginAdminUnauthorized() {
        this.webTestClient
                .mutate().filter(basicAuthentication(adminname, fail_password)).build()
                .post().uri("/auth/login")
                .bodyValue(new Login(adminname, "342456"))
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
