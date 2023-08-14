package com.example.service;

import java.util.List;

import com.example.payloads.PostDto;
import com.example.payloads.PostResponse;

public interface PostService {
	
	//create
	public PostDto createPost(PostDto postDto, int userId, int categoryId);
	
	//update
	public PostDto updatePost(PostDto postDto, int postId);
	
	//delete
	public void deletePost(int postId);
	
	//get all posts
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	//get single post
	public PostDto getPostById(int postId);
	
	//get all post by category
	public List<PostDto> getPostByCategory(int categoryId);
	
	//get all post by user
	public List<PostDto> getPostByUser(int userId);
	
	//search posts
	public List<PostDto> searchPosts(String keyword);
	
	
	
	
}
