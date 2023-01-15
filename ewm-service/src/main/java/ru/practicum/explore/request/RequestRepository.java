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
    List<Request> getRequestsByEvent(Integer eventId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE event = ? AND requester = ?")
    Request getRequestsByRequester(Integer eventId, Integer reqId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM requests " +
                    "WHERE requester = ?")
    List<Request> getRequestsByUserId(Integer userId);
}
