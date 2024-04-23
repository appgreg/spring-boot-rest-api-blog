package springboot.rest.api.blog.service;

import springboot.rest.api.blog.payload.CommentDto;

import java.util.List;

public interface CommmentService {

    CommentDto createComment(long idPost, CommentDto commentDto);

    List<CommentDto> getAllCommentsByIdPost(long idPost);

    CommentDto getCommentById(long idPost, long idComment);

    CommentDto updateComment(long idPost, long idComment, CommentDto commentDto);

    void deleteComment(long idPost, long idComment);
}
