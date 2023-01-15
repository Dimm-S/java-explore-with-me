package ru.practicum.explore.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.CategoryService;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.user.UserService;
import ru.practicum.explore.user.model.User;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventMapper {
    private final CategoryService categoryService;
    private final UserService userService;

    public EventFullDto mapToFullDto(Event event, Category category, User user) {
        return new EventFullDto(
                event.getAnnotation(),
                new EventFullDto.CategoryDto(category.getId(), category.getName()),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                event.getId(),
                new EventFullDto.UserShortDto(user.getId(), user.getName()),
                new EventFullDto.Location(event.getLocationLat(), event.getLocationLon()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public EventFullDto mapToFullDto(Event event) {
        return new EventFullDto(
                event.getAnnotation(),
                new EventFullDto.CategoryDto(
                        categoryService.getCategory(event.getCategory()).getId(),
                        categoryService.getCategory(event.getCategory()).getName()),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                event.getId(),
                new EventFullDto.UserShortDto(
                        userService.getUser(event.getInitiator()).getId(),
                        userService.getUser(event.getInitiator()).getName()),
                new EventFullDto.Location(event.getLocationLat(), event.getLocationLon()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public EventShortDto mapToShortDto(Event event) {
        return new EventShortDto(
                event.getAnnotation(),
                new EventFullDto.CategoryDto(
                        categoryService.getCategory(event.getCategory()).getId(),
                        categoryService.getCategory(event.getCategory()).getName()),
                event.getConfirmedRequests(),
                event.getEventDate(),
                event.getId(),
                new EventFullDto.UserShortDto(
                        userService.getUser(event.getInitiator()).getId(),
                        userService.getUser(event.getInitiator()).getName()),
                event.getPaid(),
                event.getTitle(),
                event.getViews()
        );
    }

    public Event mapNewToEvent(NewEventDto newEventDto, Integer userId) {
        String state;
        if (newEventDto.getRequestModeration().equals(true)) {
            state = "PENDING";
        } else {
            state = "PUBLISHED";
        }
        return new Event(
                null,
                newEventDto.getAnnotation(),
                newEventDto.getCategory(),
                0,
                LocalDateTime.now(),
                newEventDto.getDescription(),
                newEventDto.getEventDate(),
                userId,
                newEventDto.getLocation().getLat(),
                newEventDto.getLocation().getLon(),
                newEventDto.getPaid(),
                newEventDto.getParticipantLimit(),
                null,
                newEventDto.getRequestModeration(),
                state,
                newEventDto.getTitle(),
                0L
        );
    }
}
