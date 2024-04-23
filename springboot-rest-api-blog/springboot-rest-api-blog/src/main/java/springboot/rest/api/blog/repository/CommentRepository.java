package springboot.rest.api.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rest.api.blog.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long idPost);
}
