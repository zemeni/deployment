package com.example.testdevops.service;

import com.example.testdevops.payload.CommentDto;

import java.util.List;


public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> findCommentsByPostId(long postId);

    CommentDto findCommentsById(long postId, long commentId);

    CommentDto updateComment(CommentDto commentDto, long postId, long commentId);

    void deleteComment(long postId, long commentId);
}
