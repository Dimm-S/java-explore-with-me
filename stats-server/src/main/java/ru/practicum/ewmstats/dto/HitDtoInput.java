package ru.practicum.ewmstats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HitDtoInput {
    private String app;
    private String uri;
    private String ip;
}
