package ru.practicum.explore.compilations;

import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.explore.compilations.dto.CompilationDto;
import ru.practicum.explore.compilations.dto.NewCompilationDto;

import java.util.List;

public interface CompilationService {

    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilation(Integer compId);

    CompilationDto saveCompilation(NewCompilationDto compilationDto);

    void deleteCompilation(Integer compId);

    void deleteEventFromCompilation(Integer compId, Integer eventId);

    void unpinCompilation(Integer compId);

    void pinCompilation(Integer compId);
}
