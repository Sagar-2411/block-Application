package com.codewithdurgesh.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payload.PostDto;
import com.codewithdurgesh.blog.payload.PostResponse;
import com.codewithdurgesh.blog.services.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@Slf4j
public class PostController {

	@Autowired
	private PostService postService;

	// create
	// comments
	/**
	 * @author Sagar
	 * @apiNote to save post data in database
	 * @param postDto
	 * @since 1.0v
	 * @return
	 */
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		log.info("Entering request for the save post data in controller layer ");
		PostDto postCreate = this.postService.postCreate(postDto, userId, categoryId);
		log.info("complite request for get post data in controller layer ");
		return new ResponseEntity<PostDto>(postCreate, HttpStatus.CREATED);

	}

	// get by user
	/**
	 * @author Sagar
	 * @apiNote to get data of user from user table
	 * @param userId
	 * @since 1.0v
	 * @return
	 */
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		log.info("Entering request for the get post user data in controller layer ");
		List<PostDto> posts = postService.getPostByUser(userId);
		log.info("complite request for get post user data in controller layer ");
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get by category
	/**
	 * @author Sagar
	 * @apiNote TO get data of category from category table
	 * @param CategoryId
	 * @since 1.0vv
	 * @return
	 */
	@GetMapping("/category/{CategoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostBaCategory(@PathVariable Integer CategoryId) {
		log.info("Entering request for the get post category data in controller layer ");
		List<PostDto> posts = postService.getPostByCategory(CategoryId);
		log.info("complite request for get  post category data in controller layer ");

		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get all post
	/**
	 * @author Sagar
	 * @apiNote here we do Pagination and sorting
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @since 1.0v
	 * @return
	 */
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "postId",required = false) String sortDir) {
		log.info("Entering request for the all post data in controller layer ");
		PostResponse postResponse = this.postService.getAllpost(pageNumber, pageSize, sortBy,sortDir);
		log.info("complite request for get all  post data in controller layer ");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// get post datail by id
	/**
	 * @author Sagar
	 * @apiNote to get post detail from database
	 * @param postId
	 * @since 1.0v
	 * @return
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		log.info("Entering request for the get post data in controller layer ");
		PostDto postById = this.postService.getPostById(postId);
		log.info("complite request for get post data in controller layer ");
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);

	}
}
