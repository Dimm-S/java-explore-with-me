package ru.practicum.ewmstats;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewmstats.dto.HitDtoInput;
import ru.practicum.ewmstats.dto.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;
    private final HitMapper hitMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS]");

    @Override
    public void saveHit(String string) {
        Gson gson = new Gson();
        HitDtoInput hitDtoInput = gson.fromJson(string, HitDtoInput.class);
        hitRepository.save(hitMapper.mapInputToHit(hitDtoInput));
        System.out.println("Хит записан");
    }

    @Override
    public List<ViewStats> getStats(String start, String end) {
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
        return hitRepository.getStats(startDateTime, endDateTime);
    }
}
