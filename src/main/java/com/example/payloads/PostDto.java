package com.example.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	
	private int postId;
	@NotBlank
	@Size(min=6,message="Give atleast 6 charectors title")
	private String title;
	@NotBlank
	@Size(min=10,message="Content shold be more than 10 charactors")
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
}
