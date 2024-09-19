package com.springboot.app.controller;

import com.springboot.app.payload.CommentDto;
import com.springboot.app.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
