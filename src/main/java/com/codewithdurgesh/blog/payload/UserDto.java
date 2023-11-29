package com.codewithdurgesh.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=2, message="User must be min 2 charector")
	private String name;
	
	@Email(message="Email address not valid")
	private String email;
	
	@NotNull
	@Size(min=3, max=20,message="password must be min of 3 and max of 20 char")
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message="password first latter must be capital , add some special symbol,add some number")
	private String passward;
	
	@NotNull
	private String about;
}
