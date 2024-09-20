package com.springboot.app.controller;

import com.springboot.app.payload.CommentDto;
import com.springboot.app.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable("postId") long postId,@RequestBody CommentDto commentDto){

        CommentDto resDto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(resDto, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") long postId){

        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));

    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId
            ,@PathVariable("commentId") long commentId){

        return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId
            ,@PathVariable("commentId") long commentId
            ,@RequestBody CommentDto commentDto
    ){

        return new ResponseEntity<>(commentService.updateComment(postId,commentId,commentDto),HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId
            ,@PathVariable("commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return ResponseEntity.ok("comment deleted successfully") ;

    }

}
