package com.micro.question_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;


@RestController
public class QuestionsController {
	
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
