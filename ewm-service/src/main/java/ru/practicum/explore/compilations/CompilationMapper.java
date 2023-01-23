package ru.practicum.explore.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.compilations.dto.CompilationDto;
import ru.practicum.explore.compilations.dto.NewCompilationDto;
import ru.practicum.explore.compilations.model.Compilation;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationMapper {

    public Compilation mapNewDtoToCompilation(NewCompilationDto dto) {
        return new Compilation(
                null,
                dto.getPinned(),
                dto.getTitle()
        );
    }

    public CompilationDto mapToCompilationDto(Compilation compilation, List<EventShortDto> events) {
        return new CompilationDto(
                events,
                compilation.getId(),
                compilation.getPinned(),
                compilation.getTitle()
        );
    }
}
