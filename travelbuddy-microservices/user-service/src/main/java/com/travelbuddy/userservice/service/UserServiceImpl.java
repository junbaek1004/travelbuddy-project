package com.travelbuddy.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelbuddy.userservice.dto.UserDTO;
import com.travelbuddy.userservice.model.User;
import com.travelbuddy.userservice.repository.UserRepository;
import com.travelbuddy.userservice.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public User registerUser(UserDTO userDTO) {
		if(userRepository.existsByEmail(userDTO.getEmail())) {
			throw new RuntimeException("User with email already exists");
		}
		
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		
		return userRepository.save(user);
	}
	
	@Override
	public boolean userExists(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Override
	public String login(String email, String rawpassword) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		if (!passwordEncoder.matches(rawpassword, user.getPassword())) {
			throw new RuntimeException("Invalid password");
			
		}
		
		return jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
	}
	
	
}
