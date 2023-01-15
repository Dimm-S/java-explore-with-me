package ru.practicum.explore.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.utils.Create;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class NewCategoryDto {
    @NotNull(groups = {Create.class})
    String name;
}
