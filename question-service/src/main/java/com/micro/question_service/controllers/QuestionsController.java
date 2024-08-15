package com.micro.question_service.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.micro.question_service.jwt.JwtUtils;
import com.micro.question_service.services.CustomUserDetailsService;


@RestController
public class QuestionsController {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
	@GetMapping("/auth")
	public ResponseEntity<String> authenticateUser(@RequestHeader String uname,@RequestHeader String pwd){
		 Authentication authentication;
	        try {
	            authentication = authenticationManager
	                    .authenticate(new UsernamePasswordAuthenticationToken(uname, pwd));
	        } catch (AuthenticationException exception) {
	    
	            return new ResponseEntity<String>("Invalid Credentials !", HttpStatus.NOT_FOUND);
	        }

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(jwtToken.toString());
	}
	@GetMapping("/public")
	public ResponseEntity<String> getDataForPublic(){
		return ResponseEntity.ok("Hii there, This is public controller");
	}
	@GetMapping("/user")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<String> getDataForUser(){
		return ResponseEntity.ok("Hii there, This is user controller");
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<String> getDataForAdmin(){
		return ResponseEntity.ok("Hii there, This is admin controller");
	}
}
