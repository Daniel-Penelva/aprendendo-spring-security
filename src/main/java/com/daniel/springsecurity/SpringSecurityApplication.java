package com.daniel.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	String privateRoute(){
		return "<h1> Via privado, apenas pessoas autorizadas! </h1>";
	}
}
