package com.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.User;
import com.info.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl service;

	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User saveUser = service.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);

	}
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId){
		User user = service.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers = service.getAllUsers();
		return ResponseEntity.ok(allUsers);
		
	}

}
