package ru.practicum.explore.request;

import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.model.Request;

import java.util.List;

public interface RequestService {
    List<Request> getRequestsByEvent(Integer eventId);

    Request getRequestByReqId(Integer eventId, Integer reqId);

    void saveRequest(Request request);

    List<ParticipationRequestDto> getRequestsByUserId(Integer userId);

    ParticipationRequestDto saveRequest(Integer userId, ParticipationRequestDto requestDto);

    ParticipationRequestDto cancelRequest(Integer userId, Integer requestId);
}
