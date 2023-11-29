package com.codewithdurgesh.blog.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=3,message="size must be geter than 3")
	private String categoryTitle;

	@NotBlank
	@Size(min=3,max=50,message="size is greater than 50 and minimum 3")
	private String categoryDescription;
}
