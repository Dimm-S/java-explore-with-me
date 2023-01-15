package ru.practicum.explore.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.category.model.Category;

public interface CategoryReposirory extends JpaRepository<Category, Integer> {
}
