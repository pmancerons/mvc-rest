package curso.springframework.mvcrest.services;

import curso.springframework.mvcrest.api.v1.mapper.CategoryMapper;
import curso.springframework.mvcrest.api.v1.model.CategoryDTO;
import curso.springframework.mvcrest.domain.Category;
import curso.springframework.mvcrest.repositories.CategoryRepository;
import curso.springframework.mvcrest.services.CategoryService;
import curso.springframework.mvcrest.services.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    public static final String TEST_CATEGORY = "testCategory";
    public static final long ID = 22l;

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoriesDTO =  categoryService.getAllCategories();

        assertEquals(3, categoriesDTO.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category();
        category.setName(TEST_CATEGORY);
        category.setId(ID);

        Mockito.when(categoryRepository.findByName(ArgumentMatchers.anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(TEST_CATEGORY);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(TEST_CATEGORY,categoryDTO.getName());
    }
}