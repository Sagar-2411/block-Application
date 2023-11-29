package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entitys.Post;
import com.codewithdurgesh.blog.payload.PostDto;
import com.codewithdurgesh.blog.payload.PostResponse;

public interface PostService  {

	//create
	PostDto postCreate(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	PostDto UpdatPost(PostDto PostDto, Integer postId);
	
	//getAll
	PostResponse  getAllpost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	//getSingle POST
	PostDto getPostById(Integer postId);
	
	//delete
		void DeletePost(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by User
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	List<Post> searchPost(String keyword);
	
	
	
	
}
