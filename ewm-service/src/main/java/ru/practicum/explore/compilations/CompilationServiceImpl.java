package ru.practicum.explore.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.explore.compilations.dto.CompilationDto;
import ru.practicum.explore.compilations.dto.NewCompilationDto;
import ru.practicum.explore.compilations.model.Compilation;
import ru.practicum.explore.compilations.model.EventsCompilations;
import ru.practicum.explore.event.EventService;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventsCompilationsService eventsCompilationsService;
    private final EventService eventService;
    private final CompilationMapper compilationMapper;

    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Compilation> compilations;
        if (pinned == null) {
            compilations = compilationRepository.findAll(pageable);
        } else {
            compilations = compilationRepository.getCompilations(pinned, pageable);
        }

        List<CompilationDto> compilationDtoList = new ArrayList<>();
        for (Compilation compilation : compilations) {
            List<EventsCompilations> eventsCompilations =
                    eventsCompilationsService.getCompilation(compilation.getId());
            List<Long> eventIds = eventsCompilations.stream()
                    .map(EventsCompilations::getEventId)
                    .collect(Collectors.toList());
            List<EventShortDto> events = new ArrayList<>();
            if (eventIds.size() != 0) {
                events = eventService.getEventsListByIdsList(eventIds);
            }
            CompilationDto compilationDto = compilationMapper.mapToCompilationDto(compilation, events);
            compilationDtoList.add(compilationDto);
            }
        return compilationDtoList;
    }

    @Override
    public CompilationDto getCompilation(Long compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId);

        List<EventsCompilations> eventsCompilations =
                eventsCompilationsService.getCompilation(compilation.getId());
        List<Long> eventIds = eventsCompilations.stream()
                .map(EventsCompilations::getEventId)
                .collect(Collectors.toList());
        List<EventShortDto> events = eventService.getEventsListByIdsList(eventIds);
        CompilationDto compilationDto = compilationMapper.mapToCompilationDto(compilation, events);

        return compilationDto;
    }

    @Override
    public CompilationDto saveCompilation(NewCompilationDto compilationDto) {
        List<Long> eventIds = compilationDto.getEvents();
        eventService.checkEventsExist(eventIds);
        compilationRepository.save(compilationMapper.mapNewDtoToCompilation(compilationDto));
        Compilation compilation = compilationRepository.getCompilationByTitle(compilationDto.getTitle());

        for (Long eventId : eventIds) {
            eventsCompilationsService.saveEventCompilation(
                    new EventsCompilations(compilation.getId(), eventId)
            );
        }
        return getCompilation(compilation.getId());
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationRepository.deleteById(compId);
        //todo удалить из ev_comp ???
    }

    @Override
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        eventsCompilationsService.deleteEventFromCompilation(compId, eventId);
    }

    @Override
    public void unpinCompilation(Long compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId);
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    public void pinCompilation(Long compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId);
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }
}
