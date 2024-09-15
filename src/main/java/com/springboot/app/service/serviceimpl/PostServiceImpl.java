package com.springboot.app.service.serviceimpl;

import com.springboot.app.entity.Post;
import com.springboot.app.payload.PostDto;
import com.springboot.app.repository.PostRepository;
import com.springboot.app.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert dto to entity and save into db
        Post post=mapToEntity(postDto);

//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());

        Post newPost = postRepository.save(post);


        //convert post to dto and return dto
        PostDto postResponse=mapToDto(newPost);

//        PostDto postResponse=new PostDto();
//        postResponse.setId(newPost.getId());
//        postResponse.setTitle(newPost.getTitle());
//        postResponse.setContent(newPost.getContent());
//        postResponse.setDescription(newPost.getDescription());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        List<PostDto> postDtos=new ArrayList<>();

        postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());


        return postDtos;
    }

    //convert Entity to Dto
    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;
    }

    //convert Dto to Entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        return post;
    }
}
