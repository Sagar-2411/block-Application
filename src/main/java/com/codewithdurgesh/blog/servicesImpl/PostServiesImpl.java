package com.codewithdurgesh.blog.servicesImpl;

import java.util.Comparator;
import org.springframework.data.domain.Sort;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.hibernate.hql.internal.classic.GroupByParser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.config.constants.AppConstant;
import com.codewithdurgesh.blog.entitys.Category;
import com.codewithdurgesh.blog.entitys.Post;
import com.codewithdurgesh.blog.entitys.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payload.PostDto;
import com.codewithdurgesh.blog.payload.PostResponse;
import com.codewithdurgesh.blog.repository.CategoryRepo;
import com.codewithdurgesh.blog.repository.PostRepo;
import com.codewithdurgesh.blog.repository.UserRepo;
import com.codewithdurgesh.blog.services.PostService;



@Service
public class PostServiesImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	

	Logger logger= LoggerFactory.getLogger(PostServiesImpl.class);
	
	@Override
	public PostDto postCreate(PostDto postDto,Integer userId,Integer categoryId) {
		
		logger.info("Entering dao class for save post data ");
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found+categoryId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.Not_Found+categoryId));
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newSave = this.postRepo.save(post);
		logger.info("complete dao call for save post data");
		return this.modelMapper.map(newSave, PostDto.class);
	}

	@Override
	public PostDto UpdatPost(PostDto PostDto, Integer postId) {
		
		
		return null;
	}

	@Override
	public PostResponse getAllpost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {  //here use  postResponse insted of List<postDto> because here we pass custuzise detail of pagination that is page number no of pages 
		logger.info("Entering dao class for all post data ");
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		//pagePost is gives all information about no of pages , no of contains , islast pase all  that things
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allPost = pagePost.getContent();
		List<PostDto> postDtos = allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(postDtos);                 //pagePost is gives all information about no of pages , no of contains , islast pase all  that things
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		logger.info("complete dao call for all post data");
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		logger.info("Entering dao class for get  post data");
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found+postId));
		logger.info("complete dao call for get post  data");
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void DeletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		logger.info("Entering dao class for get post category data");
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(AppConstant.Not_Found+categoryId));	
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> collect = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		logger.info("complete dao call for get post category data");
		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		logger.info("Entering dao call for get post user  data");
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.Not_Found+userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> collect = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		logger.info("complete dao call for get post for user data");
		return collect;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
