package ru.practicum.explore.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.practicum.explore.event.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>,
        QuerydslPredicateExecutor<Event> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM events " +
                    "WHERE id in ?")
    List<Event> getEventsListByIdList(List<Integer> idList);
}
