package ru.practicum.explore.category;

import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategory(Long id);

    CategoryDto patchCategory(CategoryDto categoryDto);

    CategoryDto saveCategory(NewCategoryDto categoryDto);

    void deleteCategory(Long catId);
}
