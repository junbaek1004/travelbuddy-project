package com.travelbuddy.userservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelbuddy.userservice.dto.LoginRequestDTO;
import com.travelbuddy.userservice.dto.UserDTO;
import com.travelbuddy.userservice.model.User;
import com.travelbuddy.userservice.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
		User savedUser = userService.registerUser(userDTO);
		return ResponseEntity.ok(savedUser);
	}
	
	@GetMapping("/exists")
	public ResponseEntity<Boolean> checkUserExists(@RequestParam String email) {
		boolean exists = userService.userExists(email);
		return ResponseEntity.ok(exists);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginDto) {
	    String token = userService.login(loginDto.getEmail(), loginDto.getPassword());

	    Map<String, String> response = new HashMap<>();
	    response.put("token", token); 

	    return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/secure")
	public ResponseEntity<String> secureTest() {
		return ResponseEntity.ok("You accessed a protected endpoint!");
	}
}
