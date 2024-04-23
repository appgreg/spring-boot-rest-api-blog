package springboot.rest.api.blog.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.rest.api.blog.entity.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
