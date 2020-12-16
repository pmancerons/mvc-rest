package curso.springframework.mvcrest.api.v1.mapper;

import curso.springframework.mvcrest.api.v1.model.CategoryDTO;
import curso.springframework.mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category source);

    Category categoryDtoToCategory(CategoryDTO source);
}
