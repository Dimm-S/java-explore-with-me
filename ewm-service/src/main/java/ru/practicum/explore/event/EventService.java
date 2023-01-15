package ru.practicum.explore.event;

import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventRequest;
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

    List<EventShortDto> getEventsListByIdsList(List<Integer> idList);

    EventFullDto getEvent(Long id, String ip);

    List<EventShortDto> getEventsByUserId(Integer userId, Integer from, Integer size);

    EventFullDto patchEventByUserId(Integer userId, UpdateEventRequest updateEventRequest);

    EventFullDto updateEvent(Integer eventId, NewEventDto updatedEvent);

    EventFullDto saveEvent(Integer userId, NewEventDto newEventDto);

    EventFullDto getEventByUserIdAndEventId(Integer userId, Integer eventId);

    EventFullDto eventCancellation(Integer userId, Integer eventId);

    List<ParticipationRequestDto> getEventParticipationByUserId(Integer userId, Integer eventId);

    ParticipationRequestDto confirmRequest(Integer userId, Integer eventId, Integer reqId);

    ParticipationRequestDto declineRequest(Integer userId, Integer eventId, Integer reqId);

    EventFullDto publishEvent(Integer eventId);

    EventFullDto rejectEvent(Integer eventId);
}
