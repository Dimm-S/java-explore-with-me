package ru.practicum.mainsvc.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainsvc.category.model.Category;

public interface CategoryReposirory extends JpaRepository<Category, Long> {

    Boolean existsCategoryByName(String name);
}
