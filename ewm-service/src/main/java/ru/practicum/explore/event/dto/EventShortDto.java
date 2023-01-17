package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventShortDto {
    private String annotation;
    private EventFullDto.CategoryDto category;
    private Integer confirmedRequests;
    private String eventDate;
    private Long id;
    private EventFullDto.UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}
