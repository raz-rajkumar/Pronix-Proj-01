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
public class StudentConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter filter;
	

//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource)
//	{
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

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests(
//				configurer -> configurer.requestMatchers(HttpMethod.GET, "/std/getAll","/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").hasRole("USER")
//				.requestMatchers(HttpMethod.DELETE,"/std/delete/**").hasRole("USER")
//				.requestMatchers(HttpMethod.GET,"/std/getById/**").hasRole("USER")
//				.requestMatchers(HttpMethod.PUT,"/std/update/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST,"/std/save").hasRole("ADMIN")
//				);
//
//		http.httpBasic(Customizer.withDefaults());
//
//		http.csrf(csrf -> csrf.disable());
//		return http.build();
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
