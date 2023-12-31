package com.codewithdurgesh.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryResponse {

		private List<CategoryDto> content;
		private int pageNumber;
		private int pageSize;
		private long totalElements;
		private int totalPages;
		private boolean lastPage;
		
	}


