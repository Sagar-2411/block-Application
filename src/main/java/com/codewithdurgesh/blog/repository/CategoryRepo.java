package com.codewithdurgesh.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entitys.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
