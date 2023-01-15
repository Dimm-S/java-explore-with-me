package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.exception.BadRequestException;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryReposirory categoryReposirory;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Category> categories = categoryReposirory.findAll(pageable);
        return StreamSupport.stream(categories.spliterator(), false)
                .map(categoryMapper::mapCategoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        return categoryMapper.mapCategoryToDto(categoryReposirory.findById(id).get());
    }

    @Override
    public CategoryDto patchCategory(CategoryDto categoryDto) {
        //todo имя должно быть уникальным
        if (categoryDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректное тело запроса");
        }
        Category category = categoryReposirory.getReferenceById(categoryDto.getId());
        category.setName(categoryDto.getName());
        categoryReposirory.save(category);
        return categoryMapper.mapCategoryToDto(category);
    }

    @Override
    public CategoryDto saveCategory(NewCategoryDto categoryDto) {
        if (categoryDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректное тело запроса");
        }
        return categoryMapper.mapCategoryToDto(
                categoryReposirory.save(
                        categoryMapper.mapNewToCategory(categoryDto)));
    }

    @Override
    public void deleteCategory(Integer catId) {
        categoryReposirory.deleteById(catId); //todo не должно быть связанных событий
    }
}

