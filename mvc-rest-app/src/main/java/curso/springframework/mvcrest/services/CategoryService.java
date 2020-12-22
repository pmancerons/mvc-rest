package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
