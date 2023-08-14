package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
	List<Post> findByTitleContaining(String title);
	
}
