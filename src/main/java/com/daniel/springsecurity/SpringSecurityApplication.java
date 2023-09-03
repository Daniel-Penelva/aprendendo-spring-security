package com.daniel.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}

@RestController
class HttpController{

	// http://localhost:8080/public
	@GetMapping("/public")
	String publicRoute(){
		return "<h1> Via pública, fique à vontade para dar uma olhada! </h1>";
	}

	// http://localhost:8080/private
	@GetMapping("/private")
	String privateRoute(@AuthenticationPrincipal OidcUser principal){
		
		return "<h1> Via privado, apenas pessoas autorizadas! </h1>\n" +
       "<h3> Principal: " + principal + "</h3>\n" +
       "<h3> E-mail attribute: " + principal.getAttribute("email") + "</h3>\n" +
       "<h3> Authorities: " + principal.getAuthorities() + "</h3>\n" +
       "<h3> JWT: " + principal.getIdToken().getTokenValue() + "</h3>\n";
	}

	// Para acessar a página de login fornecido pelo Spring Security - http://localhost:8080/login
}
