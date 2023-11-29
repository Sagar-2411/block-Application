package com.codewithdurgesh.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

//	String resourceName;
//	String fieldName;
//	long filedValue;
//	public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {
//		super(String.format("%s not found with %s : %s", resourceName,fieldName,filedValue));
//		this.resourceName = resourceName;
//		this.fieldName = fieldName;
//		this.filedValue = filedValue;
//	}
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -529489690260220073L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
