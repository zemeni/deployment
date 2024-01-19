package com.example.testdevops.repository;

import com.example.testdevops.entity.Post;
import com.example.testdevops.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategoryId(Long categoryId);
}
