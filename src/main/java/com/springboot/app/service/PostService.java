package com.springboot.app.service;

import com.springboot.app.payload.PostDto;
import com.springboot.app.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto,Long id);

    void deletePost(Long id);

}
