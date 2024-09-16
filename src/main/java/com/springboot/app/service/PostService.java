package com.springboot.app.service;

import com.springboot.app.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto,Long id);

    void deletePost(Long id);

}
