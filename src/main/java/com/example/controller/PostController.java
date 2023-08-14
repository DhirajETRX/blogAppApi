package com.example.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.AppConstants;
import com.example.payloads.ApiResponse;
import com.example.payloads.PostDto;
import com.example.payloads.PostResponse;
import com.example.service.FileService;
import com.example.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	//create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId){
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	
	
	//get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	
	
	
	
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	
	
	//get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	
	
	
	//get single post
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable int postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	
	
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully",true),HttpStatus.OK);
	}
	
	
	
	
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @PathVariable int postId, @RequestBody PostDto postDto){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	
	
	
	
	//search post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitl(@PathVariable("keywords") String Keywords){
		List<PostDto> result = this.postService.searchPosts(Keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	
	
	
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable int postId) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	
	
	
	
	//method to serve files
	@GetMapping("/post/image/{imageName}")
	public void downloadImage(@PathVariable("imageName") String imageName, 
			HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
}
