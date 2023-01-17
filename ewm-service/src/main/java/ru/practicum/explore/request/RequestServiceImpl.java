package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.explore.event.EventRepository;
import ru.practicum.explore.event.EventService;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.model.Request;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final EventRepository eventRepository;

    @Override
    public List<Request> getRequestsByEvent(Long eventId) {
        return requestRepository.getRequestsByEvent(eventId);
    }

    @Override
    public Request getRequestByReqId(Long eventId, Long reqId) {
        return requestRepository.getRequestsByReqId(eventId, reqId);
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<ParticipationRequestDto> getRequestsByUserId(Long userId) {
        List<Request> requests = requestRepository.getRequestsByUserId(userId);
        return requests.stream()
                .map(requestMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto saveRequest(Long userId, Long eventId) {
        //todo проверки
        Event event = eventRepository.getReferenceById(eventId);
        if (requestRepository.getRequestByUserIdAndEventId(userId, eventId) != null ||
            event.getInitiator() == userId ||
            !event.getState().equals("PUBLISHED") ||
            (event.getConfirmedRequests() == event.getParticipantLimit() && event.getParticipantLimit() != 0)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Недопустимые условия запроса на участие");
        }
        Request newRequest = requestMapper.mapDtoToNewRequest(userId, eventId);
        if (event.getRequestModeration().equals(false)) {
            newRequest.setStatus("CONFIRMED");
        }
        requestRepository.save(newRequest);
        return requestMapper.mapToDto(newRequest);
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        Request request = requestRepository.getReferenceById(requestId);
        request.setStatus("CANCELED");
        requestRepository.save(request);
        //todo уменьшить confirmedRequests в эвенте
        return requestMapper.mapToDto(request);
    }
}
