package ru.practicum.ewmstats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewmstats.dto.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(value = "SELECT new ru.practicum.ewmstats.dto.ViewStats(h.app, h.uri, count(h.uri)) " +
            "FROM Hit as h " +
            "WHERE h.timestamp >= ?1 AND h.timestamp <= ?2 " +
            "GROUP BY h.app, h.uri")
    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end);
}
