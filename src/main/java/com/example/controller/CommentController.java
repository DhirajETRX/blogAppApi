package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.ApiResponse;
import com.example.payloads.CommentDto;
import com.example.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> addComment(@PathVariable int postId, @RequestBody CommentDto commentDto){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
	}
	
}
