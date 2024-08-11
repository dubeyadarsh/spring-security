package com.micro.question_service.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.micro.question_service.services.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class ApiSecurity  {
	private static final String[] AUTH_WHITELIST = {"/public"};
	@Autowired CustomUserDetailsService userDetailsService;
	@Bean
	SecurityFilterChain myCustomSecurityFilter(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests) -> requests.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated()).logout((logout) -> logout.permitAll());
		http.httpBasic();
		return http.build();
	}
	

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider dAp=new DaoAuthenticationProvider();
		dAp.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		dAp.setUserDetailsService(userDetailsService);
		return dAp;
	}
}