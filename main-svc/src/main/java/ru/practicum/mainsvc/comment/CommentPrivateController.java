package ru.practicum.mainsvc.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainsvc.comment.dto.CommentDto;
import ru.practicum.mainsvc.comment.dto.NewCommentDto;
import ru.practicum.mainsvc.utils.Create;
import ru.practicum.mainsvc.utils.Update;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/comments")
public class CommentPrivateController {
    private final CommentService commentService;

    @PostMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CommentDto saveComment(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @Validated({Create.class}) @RequestBody NewCommentDto dto
    ) {
        log.info("Добавление нового комментария пользователем {} к событию {}", userId, eventId);
        return commentService.saveComment(userId, eventId, dto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(
            @PathVariable Long userId,
            @PathVariable Long commentId,
            @Validated({Update.class}) @RequestBody NewCommentDto dto
    ) {
        log.info("Редактирование комментария {} пользователем {}", commentId, userId);
        return commentService.updateComment(userId, commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long userId,
            @PathVariable Long commentId
    ) {
        log.info("Редактирование комментария {} пользователем {}", commentId, userId);
        commentService.deleteComment(userId, commentId);
    }
}
