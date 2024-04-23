package springboot.rest.api.blog.service.category;

import springboot.rest.api.blog.payload.category.CategoryDto;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
}
