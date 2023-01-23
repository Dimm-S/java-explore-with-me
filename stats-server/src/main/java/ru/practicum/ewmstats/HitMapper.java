package ru.practicum.ewmstats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewmstats.dto.HitDtoInput;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HitMapper {

    public Hit mapInputToHit(HitDtoInput input) {
        return new Hit(
                null,
                input.getApp(),
                input.getUri(),
                input.getIp(),
                LocalDateTime.now()
        );
    }
}
