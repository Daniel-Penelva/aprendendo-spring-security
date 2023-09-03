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

	// http://localhost:8080/cookie - Este exemplo utiliza cookie para manter a sessão do usuário
	@GetMapping("/cookie")
	String cookieRoute(@AuthenticationPrincipal OidcUser cookie){
		
		return "<h1> Via privado, apenas pessoas autorizadas! </h1>\n" +
       "<h3> Principal: " + cookie + "</h3>\n" +
       "<h3> E-mail attribute: " + cookie.getAttribute("email") + "</h3>\n" +
       "<h3> Authorities: " + cookie.getAuthorities() + "</h3>\n" +
       "<h3> JWT: " + cookie.getIdToken().getTokenValue() + "</h3>\n";
	}


	// Para acessar a página de login fornecido pelo Spring Security - http://localhost:8080/login
}
