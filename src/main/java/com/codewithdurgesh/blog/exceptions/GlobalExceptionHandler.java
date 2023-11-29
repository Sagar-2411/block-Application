package com.codewithdurgesh.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithdurgesh.blog.helper.ApiResponse;

@RestControllerAdvice      //This annotation is use to detect all exception find exception occur entire program globally
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)   //ResourceNotFoundException  this class we are declare by costume class 
	                                                     //means where ResourceNotFoundException occur then this method execute
public ResponseEntity<ApiResponse> ResourseNotFoundExceptionHandler(ResourceNotFoundException ex){
	String message = ex.getMessage();
	ApiResponse apiResponse= new ApiResponse(message,false);
	
	
	return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	
}	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp= new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String field = ((FieldError)error).getField();
			String Message = error.getDefaultMessage();
			resp.put(field, Message);
		});		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
}
