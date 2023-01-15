package ru.practicum.explore.compilations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CompilationDto {
    private List<EventShortDto> events;
    private Integer id;
    private Boolean pinned;
    private String title;
}
