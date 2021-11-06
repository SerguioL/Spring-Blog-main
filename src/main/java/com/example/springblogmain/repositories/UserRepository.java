package com.example.springblogmain.repositories;

import com.example.springblogmain.models.Post;
import com.example.springblogmain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

