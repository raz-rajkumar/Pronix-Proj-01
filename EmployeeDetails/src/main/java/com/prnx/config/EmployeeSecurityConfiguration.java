package com.prnx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class EmployeeSecurityConfiguration {
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails raj = User.builder().username("Raj").password("{noop}123").roles("ADMIN").build();
		UserDetails deepak = User.builder().username("Deepak").password("{noop}123").roles("EMPLOYEE").build();
		return new InMemoryUserDetailsManager(raj, deepak);
	}
}
