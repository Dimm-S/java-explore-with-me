package ru.practicum.mainsvc.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mainsvc.comment.dto.CommentDto;
import ru.practicum.mainsvc.comment.dto.NewCommentDto;
import ru.practicum.mainsvc.comment.model.Comment;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentMapper {

    public CommentDto mapToDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getUserId(),
                comment.getEventId(),
                comment.getComment(),
                comment.getCreated(),
                comment.getLastChange()
        );
    }

    public Comment dtoToNewComment(NewCommentDto dto, Long userId, Long eventId) {
        return new Comment(
                null,
                userId,
                eventId,
                dto.getComment(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
