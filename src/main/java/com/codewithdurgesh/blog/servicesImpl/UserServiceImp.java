package com.codewithdurgesh.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.config.constants.AppConstant;
import com.codewithdurgesh.blog.entitys.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payload.UserDto;
import com.codewithdurgesh.blog.payload.UserResponse;
import com.codewithdurgesh.blog.repository.UserRepo;
import com.codewithdurgesh.blog.services.UserService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j     //"log" reference name is fixed in slf4j
public class UserServiceImp implements UserService {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMaper;
	
	public User dtoToUser(UserDto userDto) {             //convert Userdto to user data
	
		User user = this.modelMaper.map(userDto, User.class);//model map method and stream map method is defferent
		
//		User user= new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassward(userDto.getPassward());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	
	public UserDto userToDto(User user) {   //convert User to userDto
		UserDto usetDto=this.modelMaper.map(user, UserDto.class);
		return usetDto;
		
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		log.info("Entering dao class for save user data");
		User user=this.modelMaper.map(userDto, User.class);   //convert userDto Object into user class
		User saveUser = this.userRepo.save(user);
		log.info("complete dao call for save user data");
		return this.modelMaper.map(saveUser, UserDto.class);   //convert user to userDto
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		log.info("Entering dao class for update user data");
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found + userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassward(userDto.getPassward());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userToDto = this.userToDto(updatedUser);
		log.info("complete dao call for update user data");
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		log.info("Entering dao call for get user data");
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found + userId));
		UserDto userToDto = this.userToDto(user);
		log.info("complete dao call for get user data");
		return userToDto;
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

		log.info("Entering dao call for all user data");
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending(); //here use ternary operator
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		Page<User> pagePost = this.userRepo.findAll(p);
		List<UserDto> userDtos = pagePost.stream().map((user) ->this.modelMaper.map(user, UserDto.class)).collect(Collectors.toList());
		
		UserResponse userResponse= new UserResponse();
		userResponse.setContain(userDtos);
		userResponse.setPageNumber(pagePost.getNumber());
		userResponse.setPageSize(pagePost.getSize());
		userResponse.setTotalElements(pagePost.getTotalElements());
		userResponse.setTotalPage(pagePost.getTotalPages());
		userResponse.setLast(pagePost.isLast());
		log.info("complete dao call for all user data");
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		log.info("Entering dao call for delete user data");
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found + userId));
		this.userRepo.delete(user);
	}

}
