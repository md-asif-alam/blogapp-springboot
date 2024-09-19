package com.springboot.app.service;

import com.springboot.app.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
}
