package com.codewithdurgesh.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserResponse {

	private List<UserDto> contain;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPage;
	private boolean isLast;
}
