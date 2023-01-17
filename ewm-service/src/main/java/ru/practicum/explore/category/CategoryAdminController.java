package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;
import ru.practicum.explore.utils.Create;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PatchMapping
    public CategoryDto patchCategory(
            @RequestBody CategoryDto categoryDto) {
        log.info("Request endpoint: 'GET /admin/categories' (Изменение категории)");
        return categoryService.patchCategory(categoryDto);
    }

    @PostMapping
    public CategoryDto saveCategory(
            @Validated({Create.class}) @RequestBody NewCategoryDto categoryDto) {
        log.info("Request endpoint: 'GET /admin/categories' (Создание категории)");
        return categoryService.saveCategory(categoryDto);
    }

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable Long catId) {
        log.info("Request endpoint: 'GET /admin/categories/{}' (Удаление категории)", catId);
        categoryService.deleteCategory(catId);
    }
}
