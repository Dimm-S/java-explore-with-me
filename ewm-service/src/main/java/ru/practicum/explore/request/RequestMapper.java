package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.model.Request;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestMapper {
    public ParticipationRequestDto mapToDto(Request request) {
        return new ParticipationRequestDto(
                request.getCreated(),
                request.getEvent_id(),
                request.getId(),
                request.getRequester_id(),
                request.getStatus()
        );
    }

    public Request mapDtoToNewRequest(Long userId, Long eventId) {
        return new Request(
                null,
                LocalDateTime.now(),
                eventId,
                userId,
                "PENDING"
        );
    }
}
