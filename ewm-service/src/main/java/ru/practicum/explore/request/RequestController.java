package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.dto.ParticipationRequestDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {
    private final RequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getRequestsByUserId(
            @PathVariable Integer userId
    ) {
        log.info("Request endpoint: 'GET /users/{}/requests' (Получение заявок пользователя)", userId);
        return requestService.getRequestsByUserId(userId);
    }

    @PostMapping
    public ParticipationRequestDto saveRequest(
            @PathVariable Integer userId,
            @RequestParam Integer eventId
//            @RequestBody ParticipationRequestDto requestDto
    ) {
        log.info("Request endpoint: 'GET /users/{}/requests' (Создание нового запроса на участие)", userId);
        return requestService.saveRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable Integer userId,
            @PathVariable Integer requestId
    ) {
        log.info("Request endpoint: 'GET /users/{}/requests/{}/cancel' " +
                "(Отмена запроса на участие)", userId, requestId);
        return requestService.cancelRequest(userId, requestId);
    }
}
