package com.example.testdevops.service;

import com.example.testdevops.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto findPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
