package ru.practicum.explore.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ParticipationRequestDto {
    private LocalDateTime created;
    private Integer event;
    private Integer id;
    private Integer requester;
    private String status;
}
