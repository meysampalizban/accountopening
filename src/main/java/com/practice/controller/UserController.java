package com.practice.controller;

import com.practice.dto.Response;
import com.practice.model.User;
import com.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value = "api/v1/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private final UserService userService;
	
	@Autowired
	private UserController(UserService userService){
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id){
		
		return this.userService.getUserById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@Valid @RequestBody User user){
		return this.userService.createUser(user);
	}
}
