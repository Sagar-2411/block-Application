package com.codewithdurgesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entitys.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
