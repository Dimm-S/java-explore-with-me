package ru.practicum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class HitMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS]");


    public Hit mapInputToHit(HitDtoInput input) {
        return new Hit(
                null,
                input.getApp(),
                input.getUri(),
                input.getIp(),
                parseLocalDateTime(input.getTimestamp(), formatter)
        );
    }

    private static LocalDateTime parseLocalDateTime(CharSequence text, DateTimeFormatter formatter) {
        if (text == null) {
            return null;
        }
        return formatter.parse(text, LocalDateTime::from);
    }

}
