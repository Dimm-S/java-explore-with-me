package ru.practicum.explore.compilations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.utils.Create;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class NewCompilationDto {
    private List<Long> events;
    private Boolean pinned;
    @NotNull(groups = {Create.class})
    private String title;
}
