package com.springboot.app.controller;


import com.springboot.app.payload.PostDto;
import com.springboot.app.payload.PostResponse;
import com.springboot.app.service.PostService;
import com.springboot.app.utils.AppConstant;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstant.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
       return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
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
