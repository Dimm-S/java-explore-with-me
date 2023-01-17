package ru.practicum.explore.compilations;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.compilations.model.Compilation;
import ru.practicum.explore.compilations.model.EventsCompilations;
import ru.practicum.explore.compilations.model.EventsCompilationsId;

import java.util.List;

public interface EventsCompilationsRepository extends JpaRepository<EventsCompilations, EventsCompilationsId> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM events_compilations " +
                    "WHERE compilation_id = ?")
    List<EventsCompilations> getCompilationById(Long compilationId);
}
