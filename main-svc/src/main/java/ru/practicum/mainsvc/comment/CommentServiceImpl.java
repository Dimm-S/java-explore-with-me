package ru.practicum.mainsvc.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.mainsvc.comment.dto.CommentDto;
import ru.practicum.mainsvc.comment.dto.NewCommentDto;
import ru.practicum.mainsvc.comment.model.Comment;
import ru.practicum.mainsvc.request.RequestService;
import ru.practicum.mainsvc.request.model.Request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final RequestService requestService;

    @Override
    public List<CommentDto> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.getCommentsByUserId(userId);
        return comments.stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByEventId(Long eventId) {
        List<Comment> comments = commentRepository.getCommentsByEventId(eventId);
        return comments.stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto saveComment(Long userId, Long eventId, NewCommentDto dto) {
        /* проверка на участие пользователя в эвенте
           оставлять комментарии могут только пользователи с одобренными заявками */
        Request request = requestService.getRequestByEventIdAndRequesterId(eventId, userId);
        if (request == null || !request.getStatus().equals("CONFIRMED")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь не участвовал в событии");
        }

        if (commentRepository.findCommentByUserIdAndEventId(userId, eventId) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Комментарий уже существует, пройдите в метод update");
        }

        Comment comment = commentMapper.dtoToNewComment(dto, userId, eventId);
        return commentMapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long userId, Long commentId, NewCommentDto dto) {
        Comment comment = commentRepository.getReferenceById(commentId);
        if (!comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нельзя обновить комментарий");
        }
        comment.setComment(dto.getComment());
        comment.setLastChange(LocalDateTime.now());
        return commentMapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.getReferenceById(commentId);
        if (!comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нельзя удалить комментарий");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto moderateComment(Long commentId, NewCommentDto dto) {
        Comment comment = commentRepository.getReferenceById(commentId);
        comment.setComment(dto.getComment());
        comment.setLastChange(LocalDateTime.now());
        return commentMapper.mapToDto(commentRepository.save(comment));
    }
}
