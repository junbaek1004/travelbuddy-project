package com.travelbuddy.userservice.service;

import com.travelbuddy.userservice.dto.UserDTO;
import com.travelbuddy.userservice.model.User;

public interface UserService {

	User registerUser(UserDTO userDTO);
	boolean userExists(String email);
	String login(String email, String password);
}
