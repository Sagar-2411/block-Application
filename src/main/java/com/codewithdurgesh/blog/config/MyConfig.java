package com.codewithdurgesh.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	@Bean
	public ModelMapper modelMapper() {   //model mapper convert user to userDto
		return new ModelMapper();
		
	}

}
