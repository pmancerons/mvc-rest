package curso.springframework.mvcrest.controller.v1;

import curso.springframework.mvcrest.api.v1.model.CategoryDTO;
import curso.springframework.mvcrest.controller.v1.CategoryController;
import curso.springframework.mvcrest.services.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    public static final String NAME = "testDTO";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListAllCategories() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1l);
        categoryDTO.setName(NAME);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(2l);
        categoryDTO2.setName("testDTO2");

        List<CategoryDTO> categoriesDTO = Arrays.asList(categoryDTO,categoryDTO2);

        Mockito.when(categoryService.getAllCategories()).thenReturn(categoriesDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categories", Matchers.hasSize(2)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categories", Matchers.hasSize(2)));

    }

    @Test
    public void testGetCategoryByName() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1l);
        categoryDTO.setName(NAME);

        Mockito.when(categoryService.getCategoryByName(ArgumentMatchers.anyString())).thenReturn(categoryDTO);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/testDTO")
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)));
    }
}