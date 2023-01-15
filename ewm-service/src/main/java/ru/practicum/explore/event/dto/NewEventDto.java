package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.utils.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewEventDto {
    @NotBlank(groups = {Create.class})
    private String annotation;
    @NotNull(groups = {Create.class})
    private Integer category;
    @NotBlank(groups = {Create.class})
    private String description;
    @NotNull(groups = {Create.class})
    private LocalDateTime eventDate; //todo добавить проверку даты
    @NotNull(groups = {Create.class})
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    @NotBlank(groups = {Create.class})
    private String title;

    @Data
    @AllArgsConstructor
    public static class Location {
        Double lat;
        Double lon;
    }
}
