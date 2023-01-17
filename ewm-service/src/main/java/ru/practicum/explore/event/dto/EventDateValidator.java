package ru.practicum.explore.event.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDateValidator implements ConstraintValidator<EventDate2Hours, NewEventDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS]");


    @Override
    public void initialize(EventDate2Hours constraint) {
    }

    @Override
    public boolean isValid(NewEventDto newEventDto, ConstraintValidatorContext context) {

        if (parseLocalDateTime(newEventDto.getEventDate(), formatter).isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Недопустимая дата");
        }
        return true;
    }

    private static LocalDateTime parseLocalDateTime(CharSequence text, DateTimeFormatter formatter) {
        if (text == null) {
            return null;
        }
        return formatter.parse(text, LocalDateTime::from);
    }
}
