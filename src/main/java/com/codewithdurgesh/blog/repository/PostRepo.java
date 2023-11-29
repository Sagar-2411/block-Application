package com.codewithdurgesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithdurgesh.blog.entitys.Category;
import com.codewithdurgesh.blog.entitys.Post;
import com.codewithdurgesh.blog.entitys.User;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
