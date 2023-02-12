package ru.practicum.mainsvc.comment;

import ru.practicum.mainsvc.comment.dto.CommentDto;
import ru.practicum.mainsvc.comment.dto.NewCommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentsByUserId(Long userId);

    List<CommentDto> getCommentsByEventId(Long eventId);

    CommentDto saveComment(Long userId, Long eventId, NewCommentDto dto);

    CommentDto updateComment(Long userId, Long commentId, NewCommentDto dto);

    void deleteComment(Long userId, Long commentId);

    CommentDto moderateComment(Long commentId, NewCommentDto dto);
}
