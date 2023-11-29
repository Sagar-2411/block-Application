package com.codewithdurgesh.blog.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.config.constants.AppConstant;
import com.codewithdurgesh.blog.entitys.Category;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payload.CategoryDto;
import com.codewithdurgesh.blog.payload.CategoryResponse;
import com.codewithdurgesh.blog.payload.PostResponse;
import com.codewithdurgesh.blog.repository.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		log.info("Entering dao class for save category data"); 
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addCat = this.categoryRepo.save(cat);
		log.info("complete dao call for save category data");
		return this.modelMapper.map(addCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		log.info("Entering dao class for delete category data");
		Category Cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.Not_Found+categoryId));
		this.categoryRepo.delete(Cat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		log.info("Entering dao class for update category data");
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.Not_Found+categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());

		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatecat = this.categoryRepo.save(cat);
		log.info("complete dao call for update category data");
		return this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		log.info("Entering dao class for get category data");
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.Not_Found+categoryId));
		log.info("complete dao call for get category data");
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryResponse getCategories(Integer pageNumber,Integer pageSize) {
		log.info("Entering dao class for get all category data");
		PageRequest p = PageRequest.of(pageNumber, pageSize);
		 Page<Category> pagePost = this.categoryRepo.findAll(p);
		 List<Category> content = pagePost.getContent();
		List<CategoryDto> collect = content.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		CategoryResponse categoryResponse = new CategoryResponse();

		categoryResponse.setContent(collect);
		categoryResponse.setPageNumber(pagePost.getNumber());
		categoryResponse.setPageSize(pagePost.getSize());
		categoryResponse.setTotalElements(pagePost.getTotalElements());
		categoryResponse.setTotalPages(pagePost.getTotalPages());
		categoryResponse.setLastPage(pagePost.isLast());
		log.info("complete dao call for get all category data");	
		return categoryResponse;
	}

}
