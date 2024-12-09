package com.prnx.config;

import javax.sql.DataSource;

import org.hibernate.sql.Delete;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfiguration {

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsManager() { UserDetails
	 * raj =
	 * User.builder().username("Raj").password("{noop}123").roles("ADMIN").build();
	 * UserDetails deepak =
	 * User.builder().username("Deepak").password("{noop}123").roles("EMPLOYEE").
	 * build(); return new InMemoryUserDetailsManager(raj, deepak); }
	 */

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// define query to retrieve a user by username

		jdbcUserDetailsManager
				.setUsersByUsernameQuery("select username, password,active from members where username=?");

		// define query to retrieve the authorities/roles by username

		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,role from roles where username=?");

		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(csrf -> csrf.disable())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(configurer -> 
				configurer
				.requestMatchers("/allEmployees", "/home","/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
				.permitAll()
				.requestMatchers("/deleteEmployee/**")
				.hasRole("MANAGER")
				.requestMatchers("/save", "/updateEmployee/**", "/findEmployee/**")
				.hasRole("ADMIN"))
				.build();
	}
}
