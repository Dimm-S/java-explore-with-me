package ru.practicum.explore.event;

import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

public interface EventService {
    List<EventShortDto> getEvents(String ip,
                                  String text,
                                  List<Integer> categories,
                                  Boolean paid,
                                  String rangeStart,
                                  String rangeEnd,
                                  Boolean onlyAvailable,
                                  String sort,
                                  Integer from,
                                  Integer size);

    List<EventFullDto> getEvents(List<Integer> users,
                                  List<String> states,
                                  List<Integer> categories,
                                  String rangeStart,
                                  String rangeEnd,
                                  Integer from,
                                  Integer size);

    List<EventShortDto> getEventsListByIdsList(List<Long> idList);

    EventFullDto getEvent(Long id, String ip);

    Event getEvent(Long eventId);

    List<EventShortDto> getEventsByUserId(Integer userId, Integer from, Integer size);

    EventFullDto patchEventByUserId(Long userId, UpdateEventRequest updateEventRequest);

    EventFullDto updateEvent(Long eventId, NewEventDto updatedEvent);

    EventFullDto saveEvent(Long userId, NewEventDto newEventDto);

    EventFullDto getEventByUserIdAndEventId(Long userId, Long eventId);

    EventFullDto eventCancellation(Long userId, Long eventId);

    List<ParticipationRequestDto> getEventParticipationByUserId(Long userId, Long eventId);

    ParticipationRequestDto confirmRequest(Long userId, Long eventId, Long reqId);

    ParticipationRequestDto declineRequest(Long userId, Long eventId, Long reqId);

    EventFullDto publishEvent(Long eventId);

    EventFullDto rejectEvent(Long eventId);

    void checkEventsExist(List<Long> eventIds);
}
