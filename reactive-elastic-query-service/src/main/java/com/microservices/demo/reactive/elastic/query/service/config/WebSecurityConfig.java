package com.microservices.demo.reactive.elastic.query.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig {

/*    @Bean
    public SecurityWebFilterChain webFluxSecurityConfig(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange()
                .anyExchange()
                .permitAll();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }*/

    @Value("${security.paths-to-ignore}")
    private String[] pathsToIgnore;

    @Bean
    public SecurityWebFilterChain webFluxSecurityConfig(ServerHttpSecurity httpSecurity)  {
        httpSecurity
                .csrf(csrf -> csrf.disable())//otherwise POST is not allowed
                .authorizeExchange(authorizeHttpRequest -> authorizeHttpRequest
//                        .pathMatchers(pathsToIgnore).permitAll()
                        .pathMatchers("/**").permitAll()//.authenticated().anyExchange().hasRole("USER")
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
//      Approach 1 where we use withDefaultPasswordEncoder() method
//		while creating the user details
        *//*UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);*//*

        *//*Approach 2 where we use NoOpPasswordEncoder Bean
		while creating the user details*//*
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                .roles("USER")
                .authorities("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("USER")
                //.authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);

    }*/
}
