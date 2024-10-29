package com.practice.controller;

import com.practice.model.User;
import com.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	@ResponseBody
	public User getUserById(@PathVariable("id") Long id){
		return this.userService.getUserById(id);
	}
}
