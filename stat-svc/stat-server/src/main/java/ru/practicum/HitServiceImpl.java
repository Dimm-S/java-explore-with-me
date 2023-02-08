package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;
    private final HitMapper hitMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS][SSS]");

    @Override
    public void saveHit(HitDtoInput hit) {
        hitRepository.save(hitMapper.mapInputToHit(hit));
        log.info("Хит записан");
    }

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique) {
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        if (uris == null && !unique) {
            return hitRepository.getStatsOnlyDates(startDateTime, endDateTime);
        }
        if (uris == null) {
            return hitRepository.getStatsUniqueUris(startDateTime, endDateTime);
        }
        if (!unique) {
            return hitRepository.getStatsUrisList(startDateTime, endDateTime, uris);
        }
        return hitRepository.getStatsUniqueUrisList(startDateTime, endDateTime, uris);
    }
}
