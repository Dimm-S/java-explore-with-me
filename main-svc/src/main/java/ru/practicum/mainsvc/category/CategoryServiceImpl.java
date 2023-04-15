package ru.practicum.mainsvc.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.mainsvc.category.dto.CategoryDto;
import ru.practicum.mainsvc.category.dto.NewCategoryDto;
import ru.practicum.mainsvc.category.model.Category;
import ru.practicum.mainsvc.event.EventRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryReposirory categoryReposirory;
    private final CategoryMapper categoryMapper;
    private final EventRepository eventRepository;

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Category> categories = categoryReposirory.findAll(pageable);
        return StreamSupport.stream(categories.spliterator(), false)
                .map(categoryMapper::mapCategoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if (!categoryReposirory.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Нет такой категории");
        }
        Category category = categoryReposirory.getReferenceById(id);
        return categoryMapper.mapCategoryToDto(category);
    }

    @Override
    public CategoryDto patchCategory(Long catId, NewCategoryDto categoryDto) {
        if (categoryReposirory.existsCategoryByName(categoryDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Имя уже существует");
        }
        Category category = categoryReposirory.getReferenceById(catId);
        category.setName(categoryDto.getName());
        categoryReposirory.save(category);
        return categoryMapper.mapCategoryToDto(category);
    }

    @Override
    public CategoryDto saveCategory(NewCategoryDto categoryDto) {
        if (categoryDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректное тело запроса");
        }
        if (categoryReposirory.existsCategoryByName(categoryDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Имя уже существует");
        }
        Category category = categoryMapper.mapNewToCategory(categoryDto);
        categoryReposirory.save(category);
        return categoryMapper.mapCategoryToDto(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        if (eventRepository.findEventsByCategory(catId).size() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Нельзя удалить категорию со связанными событиями");
        }
        categoryReposirory.deleteById(catId);
    }
}

