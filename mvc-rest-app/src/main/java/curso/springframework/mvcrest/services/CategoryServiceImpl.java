package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.repositories.CategoryRepository;
import curso.springframework.mvcrest.api.v1.mapper.CategoryMapper;
import curso.springframework.mvcrest.api.v1.model.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
