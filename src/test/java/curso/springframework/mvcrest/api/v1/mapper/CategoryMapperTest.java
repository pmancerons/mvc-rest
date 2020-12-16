package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.mvcrest.api.v1.model.CategoryDTO;
import curso.springframework.mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public static final long ID = 432l;
    public static final String NAME = "cat432";

    @Test
    void categoryToCategoryDTO() {
        Category cat = new Category();
        cat.setId(ID);
        cat.setName(NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(cat);

        assertNotNull(categoryDTO);
        assertEquals(ID,categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());
    }

//    @Test
//    void categoryDtoToCategory() {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setId(ID);
//        categoryDTO.setName(NAME);
//
//        Category category = categoryMapper.categoryDtoToCategory(categoryDTO);
//
//        assertNotNull(category);
//        assertEquals(ID,category.getId());
//        assertEquals(NAME,category.getName());
//    }
}