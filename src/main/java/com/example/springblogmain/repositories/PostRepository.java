package com.example.springblogmain.repositories;

import com.example.springblogmain.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {



}
