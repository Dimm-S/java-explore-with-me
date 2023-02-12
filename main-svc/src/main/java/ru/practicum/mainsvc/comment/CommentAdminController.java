package ru.practicum.mainsvc.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainsvc.comment.dto.CommentDto;
import ru.practicum.mainsvc.comment.dto.NewCommentDto;
import ru.practicum.mainsvc.utils.Update;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentAdminController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public CommentDto moderateComment(
            @PathVariable Long commentId,
            @Validated({Update.class}) @RequestBody NewCommentDto dto
    ) {
        log.info("Модерация комментария {}", commentId);
        return commentService.moderateComment(commentId, dto);
    }
}
