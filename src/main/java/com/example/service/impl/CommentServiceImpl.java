package com.example.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.exception.ResourceNotFoundException;
import com.example.payloads.CommentDto;
import com.example.repository.CommentRepo;
import com.example.repository.PostRepo;
import com.example.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment saveedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(saveedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(com);
		
	}
	
	
	
}
