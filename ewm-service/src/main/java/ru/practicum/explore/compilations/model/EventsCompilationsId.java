package ru.practicum.explore.compilations.model;

import java.io.Serializable;

public class EventsCompilationsId implements Serializable {
    private Integer compilationId;
    private Integer eventId;

    public EventsCompilationsId(Integer compilationId, Integer eventId) {
        this.compilationId = compilationId;
        this.eventId = eventId;
    }
}
