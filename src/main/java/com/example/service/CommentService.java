package com.example.service;

import com.example.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, int postId);

	public void deleteComment(int commentId);

}
