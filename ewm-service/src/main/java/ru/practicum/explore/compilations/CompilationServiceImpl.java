package ru.practicum.explore.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
            List<Integer> eventIds = eventsCompilations.stream()
                    .map(EventsCompilations::getEventId)
                    .collect(Collectors.toList());
            List<EventShortDto> events = eventService.getEventsListByIdsList(eventIds);
            CompilationDto compilationDto = compilationMapper.mapToCompilationDto(compilation, events);
            compilationDtoList.add(compilationDto);
            }
        return compilationDtoList;
    }

    @Override
    public CompilationDto getCompilation(Integer compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId.longValue());

        List<EventsCompilations> eventsCompilations =
                eventsCompilationsService.getCompilation(compilation.getId());
        List<Integer> eventIds = eventsCompilations.stream()
                .map(EventsCompilations::getEventId)
                .collect(Collectors.toList());
        List<EventShortDto> events = eventService.getEventsListByIdsList(eventIds);
        CompilationDto compilationDto = compilationMapper.mapToCompilationDto(compilation, events);

        return compilationDto;
    }

    @Override
    public CompilationDto saveCompilation(NewCompilationDto compilationDto) {
        compilationRepository.save(compilationMapper.mapNewDtoToCompilation(compilationDto));
        Compilation compilation = compilationRepository.getCompilationByTitle(compilationDto.getTitle());
        List<Integer> eventIds = compilationDto.getEvents();
        for (Integer eventId : eventIds) {
            eventsCompilationsService.saveEventCompilation(
                    new EventsCompilations(compilation.getId(), eventId)
            );
        }
        return getCompilation(compilation.getId());
    }

    @Override
    public void deleteCompilation(Integer compId) {
        compilationRepository.deleteById(compId.longValue());
        //todo удалить из ev_comp ???
    }

    @Override
    public void deleteEventFromCompilation(Integer compId, Integer eventId) {
        eventsCompilationsService.deleteEventFromCompilation(compId, eventId);
    }

    @Override
    public void unpinCompilation(Integer compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId.longValue());
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    public void pinCompilation(Integer compId) {
        Compilation compilation = compilationRepository.getReferenceById(compId.longValue());
        compilation.setPinned(true);
        compilationRepository.save(compilation);
    }
}
