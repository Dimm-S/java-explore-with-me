package ru.practicum.mainsvc.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long userId;
    private Long eventId;
    private String comment;
    private LocalDateTime created;
    private LocalDateTime lastChange;
}
