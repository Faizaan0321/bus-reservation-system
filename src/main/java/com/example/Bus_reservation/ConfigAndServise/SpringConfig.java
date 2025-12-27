package com.example.Bus_reservation.ConfigAndServise;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SpringConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .csrf(csrf -> csrf.disable())
	                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/users/registeruser").permitAll() // public endpoint
	                        .anyRequest().authenticated()
	                )
	                .httpBasic(httpBasic->{})
	                .build();
	    }
	 
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	 @Bean
	 public PasswordEncoder passwordEncoder() {	
		return new BCryptPasswordEncoder();
	 }

	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	     CorsConfiguration configuration = new CorsConfiguration();
	     configuration.addAllowedOrigin("http://localhost:5173"); // your React dev server
	     configuration.addAllowedHeader("*");
	     configuration.addAllowedMethod("*");
	     configuration.setAllowCredentials(true);
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", configuration);
	     return source;
	 }

}
