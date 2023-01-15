package ru.practicum.explore.request.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "event")
    private Integer event_id;
    @Column(name = "requester")
    private Integer requester_id;
    @Column(name = "status")
    private String status;
}
