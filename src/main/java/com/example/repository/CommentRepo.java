package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Comment;


public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	
	
}
