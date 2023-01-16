package ru.practicum.explore.compilations.model;

import java.io.Serializable;

public class EventsCompilationsId implements Serializable {
    private Long compilationId;
    private Long eventId;

    public EventsCompilationsId(Long compilationId, Long eventId) {
        this.compilationId = compilationId;
        this.eventId = eventId;
    }
}
