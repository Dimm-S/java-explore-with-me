package ru.practicum.mainsvc.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainsvc.comment.dto.CommentDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{userId}")
    public List<CommentDto> getCommentsByUserId(@PathVariable Long userId) {
        log.info("Запрошены все комментарии пользователя {}", userId);
        return commentService.getCommentsByUserId(userId);
    }
}
