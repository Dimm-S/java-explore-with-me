package ru.practicum.explore.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.request.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE event = ?")
    List<Request> getRequestsByEvent(Long eventId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE event = ? AND requester = ?")
    Request getRequestsByRequester(Long eventId, Long reqId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE requester = ?")
    List<Request> getRequestsByUserId(Long userId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE requester = ? " +
                    "AND event = ?")
    Request getRequestByUserIdAndEventId(Long userId, Long eventId);
}
