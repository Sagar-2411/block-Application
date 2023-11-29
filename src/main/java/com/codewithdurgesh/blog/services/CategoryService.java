package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payload.CategoryDto;
import com.codewithdurgesh.blog.payload.CategoryResponse;
import com.codewithdurgesh.blog.payload.PostResponse;

public interface CategoryService {
	
	
	
	//create
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//delete
	public void deleteCategory(Integer categoryId );
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	CategoryResponse getCategories(Integer pageNumber, Integer pageSize);

}
