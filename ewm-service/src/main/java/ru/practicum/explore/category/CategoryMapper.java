package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;
import ru.practicum.explore.category.model.Category;

@Service
@RequiredArgsConstructor
public class CategoryMapper {
    public CategoryDto mapCategoryToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public Category mapNewToCategory(NewCategoryDto dto) {
        return new Category(
                null,
                dto.getName()
        );
    }
}
