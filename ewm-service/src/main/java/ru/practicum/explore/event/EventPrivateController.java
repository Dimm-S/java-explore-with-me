package ru.practicum.explore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.utils.Create;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class EventPrivateController {
    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> getEventsByUserId(
            @PathVariable Integer userId,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        log.info("Request endpoint: 'GET /users/{}/events' (Получение списка всех событий пользователя)", userId);
        return eventService.getEventsByUserId(userId, from, size);
    }

    @PatchMapping
    public EventFullDto patchEventByUserId(
            @PathVariable Integer userId,
            @RequestBody UpdateEventRequest updateEventRequest
            ) {
        log.info("Request endpoint: 'GET /users/{}/events' (Изменение события пользователем)", userId);
        return eventService.patchEventByUserId(userId, updateEventRequest);
    }

    @PostMapping
    public EventFullDto saveEvent(
            @PathVariable Integer userId,
            @Validated({Create.class}) @RequestBody NewEventDto newEventDto
            ) {
        log.info("Request endpoint: 'GET /users/{}/events' (Создание события пользователем)", userId);
        return eventService.saveEvent(userId, newEventDto);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByUserIdAndEventId(
            @PathVariable Integer userId,
            @PathVariable Integer eventId
    ) {
        log.info("Request endpoint: 'GET /users/{}/events/{}' (Получение события)", userId, eventId);
        return eventService.getEventByUserIdAndEventId(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto eventCancellation(
            @PathVariable Integer userId,
            @PathVariable Integer eventId
    ) {
        log.info("Request endpoint: 'GET /users/{}/events/{}' (Отмена события)", userId, eventId);
        return eventService.eventCancellation(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getEventParticipationByUserId(
            @PathVariable Integer userId,
            @PathVariable Integer eventId
    ) {
        log.info("Request endpoint: 'GET /users/{}/events/{}/requests'" +
                " (Получение списка участников)", userId, eventId);
        return eventService.getEventParticipationByUserId(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable Integer userId,
            @PathVariable Integer eventId,
            @PathVariable Integer reqId
    ) {
        log.info("Request endpoint: 'GET /users/{}/events/{}/requests/{}/confirm'" +
                " (Подтверждение заявки)", userId, eventId, reqId);
        return eventService.confirmRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto declineRequest(
            @PathVariable Integer userId,
            @PathVariable Integer eventId,
            @PathVariable Integer reqId
    ) {
        log.info("Request endpoint: 'GET /users/{}/events/{}/requests/{}/confirm'" +
                " (Отклонение заявки)", userId, eventId, reqId);
        return eventService.declineRequest(userId, eventId, reqId);
    }
}
