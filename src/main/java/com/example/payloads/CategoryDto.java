package com.example.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private int categoryId;
	@NotBlank
	@Size(min=4,message="Enter min 4 charectors in Title")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message = "Enter min 10 charactors in Descriptions")
	private String categoryDescription;
}
