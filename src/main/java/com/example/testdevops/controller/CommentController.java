package com.example.testdevops.controller;

import com.example.testdevops.payload.CommentDto;
import com.example.testdevops.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable(value = "postId") long postId) {
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable(value = "postId") long postId) {
        List<CommentDto> response = commentService.findCommentsByPostId(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId) {
        CommentDto commentsById = commentService.findCommentsById(postId, commentId);
        return new ResponseEntity<>(commentsById, HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "commentId") long commentId) {
        CommentDto updatedComment = commentService.updateComment(commentDto, postId, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                                @PathVariable(value = "commentId") long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
