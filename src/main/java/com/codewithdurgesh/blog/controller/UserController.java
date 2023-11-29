package com.codewithdurgesh.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.helper.ApiResponse;
import com.codewithdurgesh.blog.payload.UserDto;
import com.codewithdurgesh.blog.payload.UserResponse;
import com.codewithdurgesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Logger
	Logger logger = LoggerFactory.getLogger(UserController.class);

	// comments
	/**
	 * @author Sagar
	 * @apiNote to save user data in database
	 * @param userDto
	 * @since 1.0v
	 * @return
	 */
	@PostMapping("/user")

	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		logger.info("Entering request for the save user data in controller layer");
		UserDto createrdUserDto = this.userService.createUser(userDto);
		logger.info("complite request for save user data in controller layer ");
		return new ResponseEntity<>(createrdUserDto, HttpStatus.CREATED);

	}

	/**
	 * @author Sagar
	 * @apiNote to update user data
	 * @param usetDto
	 * @param userId
	 * @since 1.0v
	 * @return
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto usetDto, @PathVariable Integer userId) {
	
		logger.info("Entering request for the update user data in controller layer");
		UserDto updateUser = this.userService.updateUser(usetDto, userId);
		logger.info("complite request for updata user data in controller layer ");

		return ResponseEntity.ok(updateUser);

	}

	/**
	 * @author Sagar
	 * @apiNote to delete user data
	 * @param userId
	 * @since 1.0v
	 * @return
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUset(@PathVariable Integer userId) {

		logger.info("Entering request for delete user data in controller layer");
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succefully", true), HttpStatus.OK);

	}

	/**
	 * @author Sagar
	 * @apiNote to get all data from data base
	 * @since 1.0v
	 * @return
	 */
	@GetMapping("/")

	public ResponseEntity<UserResponse> getAllUser(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false )String sortDir
			) {
		logger.info("Entering request for all user in controller layer ");
		UserResponse allUsers = this.userService.getAllUsers(pageNumber, pageSize,sortBy,sortDir);
		logger.info("complite request for all user");
		return new ResponseEntity<UserResponse>(allUsers, HttpStatus.OK);

	}

	/**
	 * @author Sagar
	 * @apiNote to get single data from database
	 * @param userId
	 * @since 1.0v
	 * @return
	 */
	@GetMapping("/{userId}")

	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		logger.info("Entering request for gate user data in controller layer");
		UserDto userById = this.userService.getUserById(userId);
		logger.info("complite request for get user data controller layer");
		return ResponseEntity.ok(userById);

	}
}
