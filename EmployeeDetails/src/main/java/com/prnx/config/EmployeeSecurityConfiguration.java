package com.prnx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfiguration {
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails raj = User.builder().username("Raj").password("{noop}123").roles("ADMIN").build();
		UserDetails deepak = User.builder().username("Deepak").password("{noop}123").roles("EMPLOYEE").build();
		return new InMemoryUserDetailsManager(raj, deepak);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		
		http.authorizeHttpRequests(configurer -> 
			configurer.requestMatchers(HttpMethod.GET, "/allEmployees").hasRole("EMPLOYEE")
				);
		
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
