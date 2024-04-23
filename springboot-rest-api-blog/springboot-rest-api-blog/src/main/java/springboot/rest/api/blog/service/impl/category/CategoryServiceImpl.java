package springboot.rest.api.blog.service.impl.category;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springboot.rest.api.blog.entity.category.Category;
import springboot.rest.api.blog.payload.category.CategoryDto;
import springboot.rest.api.blog.repository.category.CategoryRepository;
import springboot.rest.api.blog.service.category.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }
}
