package com.daniel.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    /*
     * A segurança é feito através de filtros (filter), ou seja, a requisição web
     * passa pelo filtro ( o filter intercepta a requisição e checa
     * se já é autenticada ou não, senão não for autenticado nem entra no
     * controller) que modifica e transforma numa requisição adequada que será
     * tratada pela lógica de negócio.
     * Passa por parâmetro o http que é o objeto onde será configurado a segurança.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeConfig -> {
                    // Rotas que não precisam de autenticação, são elas: public e logout
                    authorizeConfig.antMatchers("/public").permitAll();
                    authorizeConfig.antMatchers("/logout").permitAll();

                    // autorização só pode ser feito por alguém autenticado
                    authorizeConfig.anyRequest().authenticated();

                }).oauth2Login(oauth2LoginConfig -> {
                    oauth2LoginConfig
                            .defaultSuccessUrl("/cookie"); // Redireciona para /private após o login            
                }).build();
    }
}
