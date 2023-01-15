package ru.practicum.explore.compilations;

import ru.practicum.explore.compilations.model.EventsCompilations;

import java.util.List;

public interface EventsCompilationsService {
    List<EventsCompilations> getCompilation(Integer compilationId);
    void saveEventCompilation(EventsCompilations eventsCompilations);
    void deleteEventFromCompilation(Integer compId, Integer eventId);
}
