package com.example.testdevops.service.impl;

import com.example.testdevops.entity.Comment;
import com.example.testdevops.entity.Post;
import com.example.testdevops.exception.BlogApiException;
import com.example.testdevops.exception.ResourceNotFoundException;
import com.example.testdevops.payload.CommentDto;
import com.example.testdevops.payload.PostResponse;
import com.example.testdevops.repository.CommentRepository;
import com.example.testdevops.repository.PostRepository;
import com.example.testdevops.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> findCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto findCommentsById(long postId, long commentId) {
        Comment comment = checkIfCommentBelongsToPost(postId, commentId);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long postId, long commentId) {
        Comment comment = checkIfCommentBelongsToPost(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updateComment = commentRepository.save(comment);
        return mapToDto(updateComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Comment comment = checkIfCommentBelongsToPost(postId, commentId);
        commentRepository.delete(comment);
    }

    private Comment checkIfCommentBelongsToPost(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId()))
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment doesn't belong to the post");
        return comment;
    }

    private CommentDto mapToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}
