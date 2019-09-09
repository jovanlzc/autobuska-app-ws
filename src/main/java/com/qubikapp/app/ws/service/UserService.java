package com.qubikapp.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.qubikapp.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(UserDto userDto,String userId);
	void deleteUser(String userId);
	boolean verifyEmailToken(String token);
}
