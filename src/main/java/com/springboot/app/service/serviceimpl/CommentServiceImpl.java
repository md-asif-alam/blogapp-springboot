package com.springboot.app.service.serviceimpl;

import com.springboot.app.entity.Comment;
import com.springboot.app.entity.Post;
import com.springboot.app.exception.BlogAPIException;
import com.springboot.app.exception.ResourceNotFound;
import com.springboot.app.payload.CommentDto;
import com.springboot.app.repository.CommentRepository;
import com.springboot.app.repository.PostRepository;
import com.springboot.app.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment=mapToEntity(commentDto);

        //retrieve post
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFound("Post","id",postId));

        //set post to comment entity
        comment.setPost(post);

        //save the comment entity to database
        Comment commentSaved = commentRepository.save(comment);
        return mapToDto(commentSaved);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        //Retrieve all comments by postId
        List<Comment> comments=commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrieve post
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFound("Post","id",postId));

        //retrieve comment
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFound("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        //retrieve post
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFound("Post","id",postId));

        //retrieve comment
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFound("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrieve post
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFound("Post","id",postId));

        //retrieve comment
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFound("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        commentRepository.delete(comment);

    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
