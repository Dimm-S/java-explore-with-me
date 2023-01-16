package ru.practicum.explore.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilations.model.EventsCompilations;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsCompilationsServiceImpl implements EventsCompilationsService{
    private final EventsCompilationsRepository eventsCompilationsRepository;

    @Override
    public List<EventsCompilations> getCompilation(Long compilationId) {
        return eventsCompilationsRepository.getCompilationById(compilationId);
    }

    @Override
    public void saveEventCompilation(EventsCompilations eventsCompilations) {
        eventsCompilationsRepository.save(eventsCompilations);
    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        eventsCompilationsRepository.delete(new EventsCompilations(compId, eventId));
    }
}
