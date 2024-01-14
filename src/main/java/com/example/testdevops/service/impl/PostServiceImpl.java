package com.example.testdevops.service.impl;

import com.example.testdevops.entity.Post;
import com.example.testdevops.exception.ResourceNotFoundException;
import com.example.testdevops.payload.PostDto;
import com.example.testdevops.payload.PostResponse;
import com.example.testdevops.repository.PostRepository;
import com.example.testdevops.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post postEntity = mapToEntity(postDto);
        Post post = postRepository.save(postEntity);
        return mapToDto(post);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equals(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> postList = page.getContent();
        List<PostDto> content = postList.stream().map(this::mapToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());

        return postResponse;
    }

    @Override
    public PostDto findPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    private Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDto mapToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
