package com.springboot.app.controller;


import com.springboot.app.payload.PostDto;
import com.springboot.app.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create Blog Post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
       return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id){
       return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") Long id){
       return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Delete post successfully",HttpStatus.OK);
    }
}
