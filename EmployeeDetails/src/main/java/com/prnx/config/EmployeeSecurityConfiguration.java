package com.prnx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class EmployeeSecurityConfiguration {
	
	
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter filter;

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsManager() { UserDetails
	 * raj =
	 * User.builder().username("Raj").password("{noop}123").roles("ADMIN").build();
	 * UserDetails deepak =
	 * User.builder().username("Deepak").password("{noop}123").roles("EMPLOYEE").
	 * build(); return new InMemoryUserDetailsManager(raj, deepak); }
	 */

//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//		// define query to retrieve a user by username
//
//		jdbcUserDetailsManager
//				.setUsersByUsernameQuery("select username, password,active from members where username=?");
//
//		// define query to retrieve the authorities/roles by username
//
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,role from roles where username=?");
//
//		return jdbcUserDetailsManager;
//	}
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		return http
//				.csrf(csrf -> csrf.disable())
//				.formLogin(Customizer.withDefaults())
//				.httpBasic(Customizer.withDefaults())
//				.authorizeHttpRequests(configurer -> 
//				configurer
//				.requestMatchers("/allEmployees", "/home","/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
//				.permitAll()
//				.requestMatchers("/deleteEmployee/**")
//				.hasRole("MANAGER")
//				.requestMatchers("/save", "/updateEmployee/**", "/findEmployee/**")
//				.hasRole("ADMIN"))
//				.build();
//	}
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/user/register","/user/login").permitAll()
						.anyRequest().authenticated())

				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
}
