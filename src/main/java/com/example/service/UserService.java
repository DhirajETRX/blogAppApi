package com.example.service;

import java.util.List;

import com.example.entity.User;
import com.example.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto updateUser(UserDto userDto, int userId);
	
	public UserDto getUserById(int userId);
	
	public List<UserDto> getAll();
	
	public void deleteUser(int userId);
}
