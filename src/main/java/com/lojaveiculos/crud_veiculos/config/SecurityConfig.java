package com.lojaveiculos.crud_veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/veiculos/novo", "/veiculos/editar/**", "/veiculos/excluir/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .exceptionHandling().accessDeniedPage("/acesso-negado");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123") // {noop} significa que não será criptografado
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
