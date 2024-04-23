package springboot.rest.api.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springboot.rest.api.blog.entity.Comment;
import springboot.rest.api.blog.entity.Post;
import springboot.rest.api.blog.exception.BlogAPIException;
import springboot.rest.api.blog.exception.ResourceNotFoundException;
import springboot.rest.api.blog.payload.CommentDto;
import springboot.rest.api.blog.repository.CommentRepository;
import springboot.rest.api.blog.repository.PostRepository;
import springboot.rest.api.blog.service.CommmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommmentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(long idPost, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = this.postRepository.findById(idPost).orElseThrow(()-> new ResourceNotFoundException("Post", "id", idPost));
        comment.setPost(post);
        Comment newComment = this.commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByIdPost(long idPost) {
        List<Comment> commentList = commentRepository.findByPostId(idPost);
        return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long idPost, long idComment) {
        Post post = this.postRepository.findById(idPost).orElseThrow(()-> new ResourceNotFoundException("Post", "id", idPost));
        Comment comment = this.commentRepository.findById(idComment).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", idComment));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long idPost, long idComment, CommentDto commentDto) {
        Post post = this.postRepository.findById(idPost).orElseThrow(()-> new ResourceNotFoundException("Post", "id", idPost));
        Comment comment = this.commentRepository.findById(idComment).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", idComment));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setContent(commentDto.getContent());

        return mapToDto(this.commentRepository.save(comment));
    }

    @Override
    public void deleteComment(long idPost, long idComment) {
        Post post = this.postRepository.findById(idPost).orElseThrow(()-> new ResourceNotFoundException("Post", "id", idPost));
        Comment comment = this.commentRepository.findById(idComment).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", idComment));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        this.commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }


    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
