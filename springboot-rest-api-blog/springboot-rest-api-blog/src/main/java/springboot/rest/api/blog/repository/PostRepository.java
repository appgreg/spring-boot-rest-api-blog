package springboot.rest.api.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rest.api.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
