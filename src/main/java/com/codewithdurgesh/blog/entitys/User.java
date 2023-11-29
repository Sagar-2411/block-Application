package com.codewithdurgesh.blog.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId", nullable=false, length=100)
	private Integer id;               //here we don't use Capital letter on veriable of literal beacuse of this keyword confuse which method it take
	
	@Column(name="userName")
	private String name;
	
	@Column(name="userEmail")
	private String email;
	
	@Column(name="userPass")
	private String passward;
	
	@Column(name="userAbout")
	private String about;
	
	@OneToMany(mappedBy = "user",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
}
