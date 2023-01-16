package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.request.dto.ParticipationRequestDto;
import ru.practicum.explore.request.model.Request;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService{
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public List<Request> getRequestsByEvent(Integer eventId) {
        return requestRepository.getRequestsByEvent(eventId);
    }

    @Override
    public Request getRequestByReqId(Integer eventId, Integer reqId) {
        return null;
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<ParticipationRequestDto> getRequestsByUserId(Integer userId) {
        List<Request> requests = requestRepository.getRequestsByUserId(userId);
        return requests.stream()
                .map(requestMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto saveRequest(Integer userId, Integer eventId) {
        //todo проверки
        Request newRequest = requestMapper.mapDtoToNewRequest(userId, eventId);
        requestRepository.save(newRequest);
        return requestMapper.mapToDto(newRequest);
    }

    @Override
    public ParticipationRequestDto cancelRequest(Integer userId, Integer requestId) {
        Request request = requestRepository.getReferenceById(requestId.longValue());
        request.setStatus("CANCELLED");
        requestRepository.save(request);
        //todo уменьшить confirmedRequests в эвенте
        return requestMapper.mapToDto(request);
    }
}
