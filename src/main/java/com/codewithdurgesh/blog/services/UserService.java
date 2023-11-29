package com.codewithdurgesh.blog.services;


import com.codewithdurgesh.blog.payload.UserDto;
import com.codewithdurgesh.blog.payload.UserResponse;

public interface UserService {
UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	UserResponse getAllUsers(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	void deleteUser(Integer userId);
}
