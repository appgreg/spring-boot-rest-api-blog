package springboot.rest.api.blog.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.rest.api.blog.payload.CommentDto;
import springboot.rest.api.blog.service.CommmentService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommmentService commmentService;

    @PostMapping("/posts/{idPost}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "idPost") long idPost,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(this.commmentService.createComment(idPost,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{idPost}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByIdPost(@PathVariable(value = "idPost") long idPost){
        return ResponseEntity.ok(this.commmentService.getAllCommentsByIdPost(idPost));
    }

    @GetMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable(value = "idPost") long idPost,
                                                      @PathVariable(value = "idComment")long idComment){
        return ResponseEntity.ok(this.commmentService.getCommentById(idPost,idComment));
    }

    @PutMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "idPost") long idPost,
                                                    @PathVariable(value = "idComment")long idComment,
                                                    @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(this.commmentService.updateComment(idPost,idComment, commentDto));
    }

    @DeleteMapping("/posts/{idPost}/comments/{idComment}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "idPost") long idPost,
                                                      @PathVariable(value = "idComment")long idComment){
        this.commmentService.deleteComment(idPost, idComment);
        return ResponseEntity.ok("Comment deleted successfully.");
    }

}
